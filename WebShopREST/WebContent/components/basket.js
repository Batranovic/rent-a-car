Vue.component("basket", { 
	data: function () {
	   return{
		   vehicles: [],
		   comments: [],
		   basket: {}
	   }
	},
	    template: 
	    `
	    <div class="container">
	    	<center><h2>Your basket</h2></center>
	    	
           <table class="rentacar-table" border="1">
			<tr>
				<th>Image</th>
				<th>Brand</th>
				<th>Model</th>
				<th>Price</th>
				<th>Rent</th>
				<th>Remove</th>
			</tr>
			<tr >
	            	<td><img :src="vehicles.picture" alt="Car Image" width="100"></td>
	            	<td> {{ vehicles.brand }}</td>
	            	<td> {{ vehicles.model }}</td>
	            	<td> {{ vehicles.price }}</td>
	            	<td><button type="sumbit"  v-on:click="rentVehicle()">Rent</button></td>
        			<td><button type="sumbit"  v-on:click="removeVehicle()">Remove</button></td>
	            
	         </tr>
	         	
		</table>
		
         </div>
	    `,
	 
    mounted () {
		axios.get(`rest/basket/getBasketForUser/` + localStorage.getItem("loggedUserId"))
			.then(response => {
				this.basket = response.data;
				axios.get(`rest/basket/getAllVehicles/` + localStorage.getItem("loggedUserId"))
					.then(response =>{
						this.vehicles = this.basket.vehicles;
					})
			})
			
        
    },
    methods:{
		
	}
  
    


});