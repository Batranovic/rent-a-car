Vue.component("managersObjects", {
	data: function() {
		return {
			object: { name: null, from: null, to: null, open: null, averageGrade: null, address: null, logo: null },
			vehicles: []
		}
	},
	template:
		`
	    <div class="container">
	    <button type="button" class="car-objects-button" v-on:click="create()">Create</button>
            <table class="rentacar-table" border="1">
              <tr>
                <td rowspan="7"><img :src="object.logo" alt="Car Image" width="100"></td>
            	<td><label>Name: </label></td>
            	<td>{{ object.name }}</td>
             </tr> 
             <tr>
            	<td><label>From: </label></td>
            	<td>{{ object.from }}</td>
             </tr> 
             <tr>
            	<td><label>To: </label></td>
            	<td>{{ object.to }}</td>
             </tr> 
             <tr>
            	<td><label>Open: </label></td>
            	<td>{{ object.open }}</td>
             </tr> 
              <tr>
            	<td><label>Grade: </label></td>
                <td>{{ object.averageGrade }}</td>
             </tr> 
             <tr>
            	<td><label>Address: </label></td>
            	<td>{{ object.address }}</td>
             </tr> 
            </table>
            <table class="rentacar-table" border="1">
	            <tr>
	            	<th>Brand</th>
	            	<th>Model</th>
	            	<th>Fuel type</th>
	            </tr>
	            <tr v-for="vehicle in vehicles" :key="vehicle.id">
	            	<td> {{ vehicle.brand }}</td>
	            	<td> {{ vehicle.model }}</td>
	            	<td> {{ vehicle.fuelType }}</td>
            		<td><button type="button" v-on:click="update(vehicle)">Update</button></td>
	                <td><button type="button" v-on:click="deleteVehicle(vehicle.id)">Delete</button></td>
	            </tr> 
            </table>
           
        </div>
	    `,
	methods: {
		create: function() {
			router.push("/createVehicle");
		},
		update: function(vehicle) {
			localStorage.setItem("vehicleForUpdate", JSON.stringify(vehicle));
			router.push(`/updateVehicle`);
		},
		deleteVehicle: function(id) {
			axios.delete(`rest/vehicles/${id}`)
				.then(response => {
					this.vehicles = this.vehicles.filter(v => v.id !== id);
				})
				.catch(error => {
					console.error("Error fetching detailed information:", error);
				});
		},
	},
	mounted() {
		axios.get(`rest/users/getById/` + localStorage.getItem("loggedUserId"))
			.then(response => {
				const id = response.data.rentACarObjectId;
				axios.get(`rest/rentACarObjects/getOneDetailed/${id}`)
					.then(response => {
						this.object = response.data;
						this.vehicles = this.object.vehicles;
						console.log('Retrieved object:', this.object);


					})
					.catch(error => {
						console.error("Error fetching detailed information:", error);
					});


			})
			.catch(error => {
				console.error("Error fetching detailed information:", error);
			});



	}

});
