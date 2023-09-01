Vue.component("basket", { 
	data: function () {
	   return{
		   vehicles: [],
		   comments: [],
		   basket: {},
		   rentalDate: null
	   }
	},
	    template: 
	    `
	    <div class="container">
	    	<center><h2>Your basket</h2></center>
	    	<table>
				 <td><label>From</label></td>
		         <td><input type="date" v-model="rentalDate.start" ></td>
		            
		         <td><label>To</label></td
	             <td><input type="date" v-model="rentalDate.end"></td>
			</table>
		
           <table class="rentacar-table" border="1">
			<tr>
				<th>Image</th>
				<th>Brand</th>
				<th>Model</th>
				<th>Price</th>
				<th>Remove</th>
			</tr>
			<tr v-for="vehicle in vehicles">
	            	<td><img :src="vehicle.image" alt="Car Image" width="100"></td>
	            	<td> {{ vehicle.brand }}</td>
	            	<td> {{ vehicle.model }}</td>
	            	<td> {{ vehicle.price }}</td>
        			<td><button type="sumbit"  v-on:click="removeVehicle(vehicle.id)">Remove</button></td>
	         </tr>
	         	
		</table>
		<tr>
			<label>Final price: </label> <input type="number" v-model="basket.price" readonly>
			<button type="sumbit"  v-on:click="rentVehicle()">Rent</button>
		
		</tr>
         </div>
	    `,
	 
    mounted () {
		this.rentalDate = JSON.parse(localStorage.getItem("rentalDate"))
		axios.get(`rest/basket/getBasketForUser/` + localStorage.getItem("loggedUserId"))
			.then(response => {
				this.basket = response.data;
				this.vehicles = this.basket.vehicles;
			})
			
        
    },
    methods:{
		removeVehicle: function(id){
			const removeFromBasketObj = {basketId : this.basket.id, vehicleId : id}
			axios.post(`rest/basket/removeFromBasket`, removeFromBasketObj)
				.then(response =>{
					this.basket = response.data;
					this.vehicles = this.basket.vehicles;
				})
		},
		rentVehicle: function(){
			axios.put(`rest/basket/buyBasket/`+ this.basket.id, this.rentalDate)
				.then(response =>{
					this.basket = response.data;
					this.vehicles = this.basket.vehicles;
				})
		}
	}
  
    


});