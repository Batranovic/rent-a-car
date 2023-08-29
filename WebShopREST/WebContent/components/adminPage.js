Vue.component("adminPage", {
	data: function() {
		return {
			users: null,
			passedId: null,
			
			searchCriteria: {
				name: '',
				surname: '',
				username: ''
			},
			filterCriteria: {
				role: '',
				customerType: ''
			},
			searchResultsBackUp : [],
			searchResults: [], // Array to store search results
			sortBy: '', // Column to sort by (e.g., 'name', 'location', 'grade')
			sortDirection: 'asc',
		}
	},
	template:
		`
	   <div class="container">
		<form v-if="users">
			<center>
			<h2><b>Administrator page</b></h2>
			</center>
				<table>
					<tr>
						<td>Name:</td>
						<td><input type = "text" v-model = "users.name" ></td>
					</tr>
					<tr>
						<td>Surname:</td>
						<td><input type = "text" v-model = "users.surname" ></td>
					</tr>
					<tr>
						<td>Username:</td>
						<td><input type = "text" v-model = "users.username"></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type = "text" v-model = "users.password"></td>
					</tr>
					<tr>
						<td>Gender:</td>
						<td><input type = "text" v-model = "users.gender"></td>
					</tr>
					<tr>
						<td>Birthday:</td>
						<td><input type = "date" v-model="formattedBirthday"></td>
					</tr>
					<button type="sumbit" v-on:click="modify()">Modify</button>
				</table>
			</form>

		<br>
		
	   <h2>All Users</h2>
      </center>
        <table>
          <tr>
            <td><label>Name:</label></td>
            <td><input type="text" v-model="searchCriteria.name"></td>
            
            <td><label>Surname:</label></td>
            <td><input type="text" v-model="searchCriteria.surname"></td>
            
            <td><label>Username:</label></td>
            <td><input type="text" v-model="searchCriteria.username"></td>
             
            <td><button type="button" v-on:click="search()">Search</button></td>
          </tr>
          
          <br>
          
          <tr>
            <td><label>Role:</label></td>
             <select v-model="filterCriteria.role" id="role">
		      <option value="customer">customer</option>
		      <option value="manager">manager</option>
		      <option value="administrator">administrator</option>
		    </select>
            
             <td><label>Customer Type:</label></td>
             <select v-model="filterCriteria.customerType" id="customerType">
		      <option value="silver">silver</option>
		      <option value="bronze">bronze</option>
		      <option value="golden">golden</option>
		    </select>
		    
		 <td><button type="button" v-on:click="filter()">Filter</button></td>
          </tr>
          
        </table>
      <table class="rentacar-table" border="1">
	  <tr bgcolor="lightgrey">
	    <th @click="sort('name')">Name 
	      <i class="arrow-icon arrow-up" :class="{ 'visible': sortBy === 'name' && sortDirection === 'asc' }"></i>
	      <i class="arrow-icon arrow-down" :class="{ 'visible': sortBy === 'name' && sortDirection === 'desc' }"></i>
	    </th>
	    <th @click="sort('surname')">Surname 
	      <i class="arrow-icon arrow-up" :class="{ 'visible': sortBy === 'surname' && sortDirection === 'asc' }"></i>
	      <i class="arrow-icon arrow-down" :class="{ 'visible': sortBy === 'surname' && sortDirection === 'desc' }"></i>
	    </th>
	    <th @click="sort('username')">Username 
	      <i class="arrow-icon arrow-up" :class="{ 'visible': sortBy === 'username' && sortDirection === 'asc' }"></i>
	      <i class="arrow-icon arrow-down" :class="{ 'visible': sortBy === 'username' && sortDirection === 'desc' }"></i>
	    </th>
	   <th @click="sort('points')">Points 
	      <i class="arrow-icon arrow-up" :class="{ 'visible': sortBy === 'points' && sortDirection === 'asc' }"></i>
	      <i class="arrow-icon arrow-down" :class="{ 'visible': sortBy === 'points' && sortDirection === 'desc' }"></i>
	    </th>
	   <th>Role</th>
	   <th>Type</th>
	  </tr>
	  
	  <tr v-for="result in searchResults" :key="result.id">
	    <td>{{ result.name }}</td>
	    <td>{{ result.surname }}</td>
	    <td>{{ result.username }}</td>
	    <td>{{ result. points }}</td>
	    <td>{{ result.role }}</td>
	    <td>{{ result.customerType }}</td>
	  </tr>
	  
	</table>
        </div>
	    `,
	computed: {
		formattedBirthday: {
			get() {
				if (this.users && this.users.birthday) {
					const parts = this.users.birthday.split("-");
					if (parts.length === 3) {
						return `${parts[2]}-${parts[1]}-${parts[0]}`;
					}
				}
				return "";
			},
			set(value) {
				const parts = value.split("-");
				if (parts.length === 3) {
					this.users.birthday = `${parts[2]}-${parts[1]}-${parts[0]}`;
				}
			}
		}
	},
	mounted() {
		this.passedId = this.$route.params.id;
		axios.get(`rest/users/searchByUsername/` + this.passedId)
			.then(response => {
				this.users = response.data
				axios.get('rest/users/')
					.then(response => {
						this.searchResults = response.data;
						this.searchResultsBackUp = response.data;
					});
	
			});


	},
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


				if (this.sortBy === 'points') {
					return (aValue - bValue) * sortFactor;
				}

				// For other columns, use localeCompare
				return aValue.localeCompare(bValue) * sortFactor;
			});
		},

		modify: function() {
			axios.put(`rest/users/update/${this.users.id}`, this.users)
				.then(response => {
					console.log('Modified:', response.data);
				})
				.catch(error => {
					console.error("Error updating user:", error);
				});
		},
		filter: function() {
	    this.searchResults = this.searchResultsBackUp.filter(obj => {
	        if (this.filterCriteria.role !== '' && obj.role !== this.filterCriteria.role) {
	            return false;
	        }
	
	       if (this.filterCriteria.customerType !== '' && obj.customerType !== this.filterCriteria.customerType) {
	            return false;
	        }
	
	        return true;
	    });
	},
		search: function() {
			axios.post('rest/users/searchUser', this.searchCriteria)
				.then(response => {
					this.searchResults = response.data;
					this.searchResultsBackUp = response.data;
				});

		},
	}
});