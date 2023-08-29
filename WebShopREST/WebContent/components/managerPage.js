Vue.component("managerPage", {
	data: function() {
		return {
			users: null,
			passedId: null,
			searchCriteria: {
				rentalDateAndTime: '',
				price: ''
			},
			searchResults: [], // Array to store search results
			sortBy: '', // Column to sort by (e.g., 'name', 'location', 'grade')
			sortDirection: 'asc',
			comments: []
		}
	},
	template:
		`
	   <div class="container">
		<form v-if="users">
			<center>
			<h2><b>Manager page</b></h2>
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
	   <h2>All rentals</h2>
	   <br>
	   
	   <table>
          <tr>
            
            <td><label>RentalDateAndTime</label></td>
            <td><input type="date" v-model="searchCriteria.rentalDateAndTime"></td>
            
            <td><label>Price:</label></td>
            <td><input type="number" v-model="searchCriteria.price"></td>
            
            <td><button type="button" v-on:click="search()">Search</button></td>
          </tr>
       </table>
        
      <table class="rentacar-table" border="1">
	  <tr bgcolor="lightgrey">
	    <th @click="sort('rentalDateAndTime')">RentalDate 
	      <i class="arrow-icon arrow-up" :class="{ 'visible': sortBy === 'location' && sortDirection === 'asc' }"></i>
	      <i class="arrow-icon arrow-down" :class="{ 'visible': sortBy === 'location' && sortDirection === 'desc' }"></i>
	    </th>
	    <th @click="sort('price')">Price 
	      <i class="arrow-icon arrow-up" :class="{ 'visible': sortBy === 'grade' && sortDirection === 'asc' }"></i>
	      <i class="arrow-icon arrow-down" :class="{ 'visible': sortBy === 'grade' && sortDirection === 'desc' }"></i>
	    </th>
	    <th>Status</th>
	    <th>Custommer</th>
	  </tr>
	  
	  <tr v-for="result in searchResults" >
	    <td>{{ result.rentalDateAndTime }}</td>
	    <td>{{ result.price }}</td>
	    <td>{{ result.status }}</td>
	    <td>{{ result.userId }}</td>
	  </tr>
	  
	</table>
	
	<br>
	<center>
		<h2>All comments</h2>	
	</center>
	<br>
	
		<table class="rentacar-table" border="1">
            <tr>
            	<th>Comment</th>
            	<th>Grade</th>
            	<th>Status</th>
            	<th>Accept</th>
            	<th>Deny</th>
            </tr>
            <tr v-for="comment in comments">
            	<td>{{ comment.text }}</td>
            	<td>{{ comment.grade }}</td>
            	<td>{{ comment.status }}</td>
            	<td><button type="sumbit" :disabled="comment.status !== 'waiting'" v-on:click="accept(comment.id)">Accept</button></td>
            	<td><button type="sumbit" :disabled="comment.status !== 'waiting'" v-on:click="deny(comment.id)">Deny</button></td>
            </tr>
          
          </table>
          
          <button  class="car-objects-button" v-on:click="myObject()">My object</button><br>
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
				axios.get(`rest/orders/getOrdersForRentObject/${this.users.rentACarObjectId}`)
					.then(response => {
						this.searchResults = response.data;
						axios.get(`rest/comments/getCommentsForRentObject/${this.users.rentACarObjectId}`)
							.then(response => {
								this.comments = response.data;

							})
							.catch(error => {
								console.error("Error fetching comments", error);
							});
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
		accept: function(id) {
			axios.put(`rest/comments/acceptComment/` + id)
				.then(response => {
					axios.get(`rest/comments/getCommentsForRentObject/${this.users.rentACarObjectId}`)
						.then(response => {
							this.comments = response.data;

						})
						.catch(error => {
							console.error("Error fetching comments", error);
						});
				});
		},

		deny: function(id) {
			axios.put(`rest/comments/denyComment/` + id)
				.then(response => {
					axios.get(`rest/comments/getCommentsForRentObject/${this.users.rentACarObjectId}`)
						.then(response => {
							this.comments = response.data;

						})
						.catch(error => {
							console.error("Error fetching comments", error);
						});
				});
		},
		myObject: function(){
		router.push("/detailedObject/"+ this.users.rentACarObjectId);
	},
	}
});