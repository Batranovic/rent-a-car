Vue.component("user-page", {
	data: function() {
		return {
			users: null,
			passedId: null,
			searchCriteria: {
				rentACarObject: '',
				rentalDateAndTimeFrom: '',
				rentalDateAndTimeTo: '',
				priceFrom: null,
				priceTo: null
			},
			searchRentalDate:{
				start: '',
				end: ''
			},
			searchResults: [], // Array to store search results
			searchResult: [],
			sortBy: '', // Column to sort by (e.g., 'name', 'location', 'grade')
			sortDirection: 'asc',
			vehicles : null
		}
	},
	template:
		`
	   <div class="container">
		<form v-if="users">
		<center>
			<h2><b>Your page</b></h2>
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
	   <h2>Your rentals</h2>
	   <br>
	   
	   <table>
          <tr>
            <td><label>Object:</label></td>
            <td><input type="text" v-model="searchCriteria.rentACarObject"></td>
            
            <td><label>From</label></td>
            <td><input type="date" v-model="searchCriteria.rentalDateAndTimeFrom"></td>
             
            <td><label>To</label></td
            <td><input type="date" v-model="searchCriteria.rentalDateAndTimeTo"></td>
            
            <td><label>Price from:</label></td>
            <td><input type="number" v-model="searchCriteria.priceFrom"></td>
            
            <td><label>Price to:</label></td>
            <td><input type="number" v-model="searchCriteria.priceTo"></td>
            
            <td><button type="button" v-on:click="search()">Search</button></td>
            
          </tr>
       </table>
        
      <table class="rentacar-table" border="1">
	  <tr bgcolor="lightgrey">
	    <th @click="sort('rentACarObject')">Object 
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
	    <th>Quit order</th>
	     <th><Label>Comment:</label></th>
	  </tr>
	  
	  <tr v-for="result in searchResults" >
	    <td>{{ result.rentACarObject }}</td>
	    <td>{{ result.rentalDateAndTime }}</td>
	    <td>{{ result.price }}</td>
	    <td>{{ result.status }}</td>
	    <td> <button type="button"  :disabled="result.status !== 'processing'" v-on:click="quitOrder(result.id)">Quit</button> </td>
	    <td> <button type="button" v-on:click="createComment(result)">Leave a comment</button> </td>
	  </tr>
	  
	</table>
	<br>
	<h2>Rent a vehicle</h2>
		<table>
			 <td><label>From</label></td>
	         <td><input type="date" v-model="searchRentalDate.start"></td>
	            
	         <td><label>To</label></td
             <td><input type="date" v-model="searchRentalDate.end"></td>
              <button type="sumbit"  v-on:click="searchVehicle()">Search</button>
		</table>
		
		<table class="rentacar-table" border="1">
			<tr>
				<th>Image</th>
				<th>Brand</th>
				<th>Model</th>
				<th>Price</th>
				<th>Type</th>
				<th>Kind</th>
				<th>Fuel</th>
				<th>Consumption</th>
				<th>Doors</th>
				<th>People</th>
				<th>Description</th>
				<th>AddToCart</th>
			</tr>
			<tr v-for="vehicle in searchResult" :key="vehicle.id">
	            	<td><img :src="vehicle.picture" alt="Car Image" width="100"></td>
	            	<td> {{ vehicle.brand }}</td>
	            	<td> {{ vehicle.model }}</td>
	            	<td> {{ vehicle.price }}</td>
	            	<td> {{ vehicle.type }}</td>
	            	<td> {{ vehicle.category }}</td>
	            	<td> {{ vehicle.fuelType }}</td>
	            	<td> {{ vehicle.consumption }}</td>
	            	<td> {{ vehicle.doorNumber }}</td>
	            	<td> {{ vehicle.peopleNumber }}</td>
	            	<td> {{ vehicle.description }}</td>
	            	<td> <button type="sumbit"  v-on:click="addToCart(vehicle.id)">AddToCart</button></td>
	            </tr>
		</table>
		 <button  class="car-objects-button" v-on:click="basket()">Basket</button><br>
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
				axios.get(`rest/orders/getOrderForUser/${this.users.id}`)
					.then(response => {
						this.searchResults = response.data;
							axios.get(`rest/vehicles/`)
								.then(response => {
									this.searchResult = response.data;
								})
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
			if (this.searchCriteria.priceFrom === null) {
				this.searchCriteria.priceFrom = -1;
			}
			if (this.searchCriteria.priceTo === null) {
				this.searchCriteria.priceTo = -1;
			}
			
			axios.post(`rest/orders/search/${this.users.id}`, this.searchCriteria)
				.then(response => {
					this.searchResults = response.data;
				});

		},
		createComment: function(order){
			 if (order.status === "returned") {
		        localStorage.setItem("commentRentACarObjectId", order.rentACarObjectId);
		        router.push("/createComment");
		    } else {
		        window.alert("Order status is not approved. Comment creation not allowed.");
		    }
   
		},
		searchVehicle : function(){
			localStorage.setItem("rentalDate", JSON.stringify(this.searchRentalDate));
			
			axios.post(`rest/vehicles/searchVehicle`, this.searchRentalDate)
					.then(response => {
						this.searchResult = response.data;
					});
		},
		basket: function(){
			router.push("/basket");
		},
		addToCart: function(id){
			const addToBasketObj = {userId : localStorage.getItem("loggedUserId"), vehicleId : id}
			axios.post(`rest/basket/addToBasket`, addToBasketObj)
				.then(response => {
					});
				
		},
		quitOrder: function(id){
			axios.put(`rest/orders/quitOrder/`+ id)
				.then(response => {
					this.searchResults = response.data;
					axios.get(`rest/orders/getOrderForUser/${this.users.id}`)
					.then(response => {
						this.searchResults = response.data; })
				});
		}
	}
});