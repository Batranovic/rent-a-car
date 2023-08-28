Vue.component("managersObjects", {
	data: function() {
		return {
			searchCriteria: {
				name: '',
				vehicleType: '',
				location: '',
				grade: null
			},
			filterCriteria: {
				vehicleType: '',
				fuel: '',
				isOpen: '',
			},
			searchResultsBackUp : [],
			searchResults: [], // Array to store search results
			sortBy: '', // Column to sort by (e.g., 'name', 'location', 'grade')
			sortDirection: 'asc',
		};
	},
	template: `
    <div class="container">
      <center>
        <h1>All available rent a car objects</h1>
      </center>
        <table>
          <tr>
            <td><label>Name:</label></td>
            <td><input type="text" v-model="searchCriteria.name"></td>
            
            <td><label>Vehicle type:</label></td>
            <td><input type="text" v-model="searchCriteria.vehicleType"></td>
            
            <td><label>Location:</label></td>
            <td><input type="text" v-model="searchCriteria.location"></td>
            
            <td><label>Grade:</label></td>
            <td><input type="text" v-model="searchCriteria.grade"></td>
            
            <td><button type="button" v-on:click="search()">Search</button></td>
          </tr>
          
          <br>
          
          <tr>
            <td><label>Vehicle type:</label></td>
             <select v-model="filterCriteria.vehicleType" id="vehicleType">
		      <option value="car">Car</option>
		      <option value="truck">Truck</option>
		      <option value="motorcycle">Motorcycle</option>
		    </select>
            
            <td><label>Fuel type:</label></td>
             <select v-model="filterCriteria.fuel" id="fuel">
		      <option value="dizel">Dizel</option>
		      <option value="petrol">Petrol</option>
		      <option value="electric">Electric</option>
		      <option value="hybrid">Hybrid</option>
		    </select>
		    
            
            <td><label>Is open:</label></td>
			  <label>
			    <input type="radio" v-model="filterCriteria.isOpen" value="yes">
			    Yes
			  </label>
			  <label>
			    <input type="radio" v-model="filterCriteria.isOpen" value="no">
			    No
			  </label>

            <td><button type="button" v-on:click="filter()">Filter</button></td>
          </tr>

        </table>
      <table class="rentacar-table" border="1">
	  <tr bgcolor="lightgrey">
	    <th @click="sort('name')">Name 
	      <i class="arrow-icon arrow-up" :class="{ 'visible': sortBy === 'name' && sortDirection === 'asc' }"></i>
	      <i class="arrow-icon arrow-down" :class="{ 'visible': sortBy === 'name' && sortDirection === 'desc' }"></i>
	    </th>
	    <th @click="sort('address')">Location 
	      <i class="arrow-icon arrow-up" :class="{ 'visible': sortBy === 'location' && sortDirection === 'asc' }"></i>
	      <i class="arrow-icon arrow-down" :class="{ 'visible': sortBy === 'location' && sortDirection === 'desc' }"></i>
	    </th>
	    <th @click="sort('averageGrade')">Grade 
	      <i class="arrow-icon arrow-up" :class="{ 'visible': sortBy === 'grade' && sortDirection === 'asc' }"></i>
	      <i class="arrow-icon arrow-down" :class="{ 'visible': sortBy === 'grade' && sortDirection === 'desc' }"></i>
	    </th>
	    <th>Logo</th>
	    <th>Open</th>
	  </tr>
	  
	  <tr v-for="result in searchResults" :key="result.id"  @click="goToDetailed(result.id)">
	    <td>{{ result.name }}</td>
	    <td>{{ result.address }}</td>
	    <td>{{ result.averageGrade }}</td>
	    <td><img :src="result.logo" alt="Car Image" width="100"></td>
	    <td>{{ result.open }}</td>
	  </tr>
	  
	</table>

    </div>
  `,
	methods: {
		sort: function(column) {
			if (this.sortBy === column) {
				this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
			} else {
				this.sortBy = column;
				this.sortDirection = 'asc';
			}
			this.sortSearchResults();
		},
		sortSearchResults: function() {
			const sortFactor = this.sortDirection === 'asc' ? 1 : -1;
			this.searchResults.sort((a, b) => {
				const aValue = a[this.sortBy];
				const bValue = b[this.sortBy];


				if (this.sortBy === 'averageGrade') {
					return (aValue - bValue) * sortFactor;
				}

				if (this.sortBy === 'location') {
					const aCity = aValue.address;
					const bCity = bValue.address;

					return aCity.localeCompare(bCity) * sortFactor;
				}

				// For other columns, use localeCompare
				return aValue.localeCompare(bValue) * sortFactor;
			});
		},

		search: function() {
			if (this.searchCriteria.grade === null) {
				this.searchCriteria.grade = -1;
			}

			axios.post('rest/rentACarObjects/search', this.searchCriteria)
				.then(response => {
					this.searchResults = response.data;
					this.searchResultsBackUp = response.data;
				});

		},
		filter: function() {
		    this.searchResults = this.searchResultsBackUp.filter(obj => {
		        if (this.filterCriteria.vehicleType !== ''  && !obj.vehicleType.includes(this.filterCriteria.vehicleType)) {
		            return false;
		        }
		
		       if (this.filterCriteria.fuel !== ''  && !obj.fuelType.includes(this.filterCriteria.fuel)) {
		            return false;
		        }
		
		        if (this.filterCriteria.isOpen !== '') {
		            if ((this.filterCriteria.isOpen === 'yes' && !obj.open) ||
		                (this.filterCriteria.isOpen === 'no' && obj.open)) {
		                return false;
		            }
		        }
		
		        return true;
				    });
		},
		 goToDetailed(id) {
        // Navigate to the detailed view and pass the shop ID as a parameter
        this.$router.push({ name: 'detailed', params: { id } });
    }


	},
	mounted() {
		axios.get('rest/rentACarObjects/')
			.then(response => {
				this.searchResults = response.data;
				this.searchResultsBackUp = response.data;
			});
	}
});
