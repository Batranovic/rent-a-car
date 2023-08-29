Vue.component("user-page", {
	data: function() {
		return {
			users: null,
			passedUsername: null,
			searchCriteria: {
				rentACarObject: '',
				rentalDateAndTime: '',
				price: ''
			},
			searchResults: [], // Array to store search results
			sortBy: '', // Column to sort by (e.g., 'name', 'location', 'grade')
			sortDirection: 'asc',
		}
	},
	template:
		`
	   <div class="container">
		<form v-if="users">
			<h2><b>Your page</b></h2>
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
	   <h2>Your rentals</h2>
	   <br>
	   
	   <table>
          <tr>
            <td><label>RentACarObject:</label></td>
            <td><input type="text" v-model="searchCriteria.rentACarObject"></td>
            
            <td><label>RentalDateAndTime</label></td>
            <td><input type="date" v-model="searchCriteria.rentalDateAndTime"></td>
            
            <td><label>Price:</label></td>
            <td><input type="number" v-model="searchCriteria.price"></td>
            
            <td><button type="button" v-on:click="search()">Search</button></td>
          </tr>
       </table>
        
      <table class="rentacar-table" border="1">
	  <tr bgcolor="lightgrey">
	    <th @click="sort('rentACarObject')">Name 
	      <i class="arrow-icon arrow-up" :class="{ 'visible': sortBy === 'name' && sortDirection === 'asc' }"></i>
	      <i class="arrow-icon arrow-down" :class="{ 'visible': sortBy === 'name' && sortDirection === 'desc' }"></i>
	    </th>
	    <th @click="sort('rentalDateAndTime')">RentalDate 
	      <i class="arrow-icon arrow-up" :class="{ 'visible': sortBy === 'location' && sortDirection === 'asc' }"></i>
	      <i class="arrow-icon arrow-down" :class="{ 'visible': sortBy === 'location' && sortDirection === 'desc' }"></i>
	    </th>
	    <th @click="sort('price')">Price 
	      <i class="arrow-icon arrow-up" :class="{ 'visible': sortBy === 'grade' && sortDirection === 'asc' }"></i>
	      <i class="arrow-icon arrow-down" :class="{ 'visible': sortBy === 'grade' && sortDirection === 'desc' }"></i>
	    </th>
	    <th>Order status</th>
	  </tr>
	  
	  <tr v-for="result in searchResults" >
	    <td>{{ result.rentACarObject }}</td>
	    <td>{{ result.rentalDateAndTime }}</td>
	    <td>{{ result.price }}</td>
	    <td>{{ result.status }}
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
		this.passedUsername = this.$route.params.username;
		axios.get(`rest/users/searchByUsername/` + this.passedUsername)
			.then(response => {
				this.users = response.data
				axios.get(`rest/orders/getOrderForUser/${this.users.id}`)
					.then(response => {
						this.searchResults = response.data;
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


				if (this.sortBy === 'price') {
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
		search: function() {
			if (this.searchCriteria.price === null) {
				this.searchCriteria.price = -1;
			}

			axios.post('rest/orders/search', this.searchCriteria)
				.then(response => {
					this.searchResults = response.data;
				});

		},
	}
});