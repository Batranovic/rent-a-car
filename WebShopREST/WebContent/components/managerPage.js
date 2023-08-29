Vue.component("managerPage", {
	data: function() {
		return {
			users: null,
			passedUsername: null,
			orders: null,
			searchCriteria: {
				rentACarObject: '',
				rentalDateAndTime: '',
				price: ''
			},
			filterCriteria: {
				rentACarObject: '',
				rentalDateAndTime: '',
				price: ''
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
			<h3><b>Your page</b></h3>
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
			<button  class="car-objects-button" v-on:click="myRentals">My rentals</button><br>
		
	  <h3>View all rentals</h3>
	   <table>
          <tr>  
            <td><label>RentalDateAndTime</label></td>
            <td><input type="time" v-model="searchCriteria.rentalDateAndTime"></td>
            
            <td><label>Price:</label></td>
            <td><input type="number" v-model="searchCriteria.price"></td>
            
            <td><button type="button" v-on:click="search()">Search</button></td>
          </tr>
        </table>
        
      <table class="rentacar-table" border="1">
	  <tr bgcolor="lightgrey">
	    <th @click="sort('rentalDateAndTime')">RentalDateAndTime 
	      <i class="arrow-icon arrow-up" :class="{ 'visible': sortBy === 'location' && sortDirection === 'asc' }"></i>
	      <i class="arrow-icon arrow-down" :class="{ 'visible': sortBy === 'location' && sortDirection === 'desc' }"></i>
	    </th>
	    <th @click="sort('price')">Price 
	      <i class="arrow-icon arrow-up" :class="{ 'visible': sortBy === 'grade' && sortDirection === 'asc' }"></i>
	      <i class="arrow-icon arrow-down" :class="{ 'visible': sortBy === 'grade' && sortDirection === 'desc' }"></i>
	    </th>
	  </tr>
	  
	  <tr v-for="result in searchResults" :key="result.id"  @click="goToDetailed(result.id)">
	    <td>{{ result.rentalDateAndTime }}</td>
	    <td>{{ result.price }}</td>
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
						this.orders = response.data
					});
			});



	},
	methods: {
		modify: function() {
			axios.put(`rest/users/update/${this.users.id}`, this.users)
				.then(response => {
					console.log('Modified:', response.data);
				})
				.catch(error => {
					console.error("Error updating user:", error);
				});
		}
	}
});