Vue.component("detailedObject", { 
	data: function () {
	   return{
		   object: { name: null, from: null, to: null, open: null,averageGrade:null, address:null, logo: null},
		   vehicles: [],
		   comments: []
	   }
	},
	    template: 
	    `
	    <div class="container">
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
            <table class="rentacar-table">
            <tr>
			        <td>
			          <ul>
			            <li v-for="vehicle in uniqueVehicles" :key="vehicle.id">
			             <img :src="vehicle.picture" alt="vehicle Image" width="100">
			              {{ vehicle.brand }},
			              {{ vehicle.model }}
			            </li>
			          </ul>
			        </td>
            </tr>
            </table>
            <table class="rentacar-table">
            <tr>
            	<tr>
			        <td>
			          <ul>
			            <li v-for="comment in comments" :key="comment.id">
			            	{{ comment.text }}
			            </li>
			          </ul>
			        </td>
            </tr>
            </tr>
            </table>
        </div>
	    `,
	 computed: {
    uniqueVehicles() {
      // Deduplicate vehicles based on brand and model
      const uniqueVehiclesMap = new Map();
      this.vehicles.forEach(vehicle => {
        const key = vehicle.brand + vehicle.model;
        if (!uniqueVehiclesMap.has(key)) {
          uniqueVehiclesMap.set(key, vehicle);
        }
      });

      return Array.from(uniqueVehiclesMap.values());
    }
  },
    mounted () {
		const id = this.$route.params.id;
    
   		 axios.get(`rest/rentACarObjects/getOneDetailed/${id}`)
        .then(response => {
            this.object = response.data;
            this.vehicles = this.object.vehicles;
            console.log('Retrieved object:', this.object);
            
            
        })
        .catch(error => {
            console.error("Error fetching detailed information:", error);
        });	
        
        
        
        axios.get(`rest/comments/getCommentsForRentObject/${id}`)
        .then(response => {
            this.comments = response.data;
            
        })
        .catch(error => {
            console.error("Error fetching comments", error);
        });	
        
    },
  
    


});