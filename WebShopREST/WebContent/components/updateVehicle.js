Vue.component("updateVehicle", {
	data: function(){
		return{

			vehicle: {brand: null, model: null, price: null, type: null, category: null, fuelType: null, 
			consumption: null, doorNumber: null, peopleNumber: null, picture: null, description: null},
			freeManagers: [],

			brandColor : '',
			modelColor: '',
			priceColor: '',
			typeColor: '',
			categoryColor: '',
			fuelTypeColor: '',
			consumptionColor: '',
			doorNumberColor: '',
			peopleNumberColor: '',
			pictureColor: '',
			descriptionColor: '',
			errorColor: '',
			errorMessage: ''
		}
	},
	
	template: `
		<div class="container">
            <h1>Update vehicle</h1>
            <form class="form-table">
            <tr>
            	<td><label>Brand *: </label></td>
            	<td><input type="text" v-model="vehicle.brand" v-bind:style="brandColor" pattern="[A-Za-z]+" title="Please enter only letters" required></td>
            </tr>
            <br>
             <tr>
            	<td><label>Model *: </label></td>
            	<td><input type="text" v-model="vehicle.model" v-bind:style="modelColor"></td>
            </tr>
            <br>
              <tr>
            	<td><label>Price *: </label></td>
            	<td><input type="text" v-model="vehicle.price" v-bind:style="priceColor"></td>
            </tr>
            <br>
            <tr>
            	<td><label>Type *: </label></td>
            	<select name="typeSelect" v-model="vehicle.type" v-bind:style="typeColor">
                    <option value="car">car</option>
                    <option value="van">van</option>
                    <option value="mobilehome">mobilehome</option>
                </select><br>
            </tr>
            <br>
             <tr>
            	<td><label>Category *: </label></td>
            	<select name="categorySelect" v-model="vehicle.category" v-bind:style="categoryColor">
                    <option value="manual">manual</option>
                    <option value="automatic">automatic</option>
                </select><br>
            </tr>
            <br>
              <tr>
            	<td><label>Fuel type *: </label></td>
            	<select name="fuelTypeSelect" v-model="vehicle.fuelType" v-bind:style="fuelTypeColor">
                    <option value="dizel">diesel</option>
                    <option value="petrol">petrol</option>
                    <option value="hybrid">hybrid</option>
                    <option value="electric">electric</option>
                </select><br>
            </tr>
            <br>
            <tr>
            	<td><label>Consumption *: </label></td>
            	<td><input type="text" v-model="vehicle.consumption" v-bind:style="fuelTypeColor"></td>
            </tr>
            <br>
            <tr>
            	<td><label>Number of doors *: </label></td>
            	<td><input type="text" v-model="vehicle.doorNumber" v-bind:style="doorNumberColor"></td>
            </tr>
            <br>
            <tr>
            	<td><label>Number of people *: </label></td>
            	<td><input type="text" v-model="vehicle.peopleNumber" v-bind:style="peopleNumberColor"></td>
            </tr>
            <br>
             <tr>
            	<td><label>Picture *: </label></td>
            	<td><input type="file" v-model="vehicle.picture" v-bind:style="pictureColor"></td>
            </tr>
            </br>
             <br>
              <tr>
            	<td><label>Description: </label></td>
                <td><input type="text" v-model="vehicle.description" v-bind:style="descriptionColor"></td>
            </tr>
            <br>
            <div class="form-row">
                <button type="button" v-on:click="update()">Update</button><br>
                <h5 v-bind:style="errorColor">{{errorMessage}}</h5>
             </div>
            </form>
        </div>
	
	`,
	
	mounted(){
		this.vehicle = JSON.parse(localStorage.getItem("vehicleForUpdate"));
		
	}, 
	
	methods: {
		update: function(){
			event.preventDefault();
			if(!this.vehicle.brand){
				this.brandColor='border-color: red';
			}else{
				this.brandColor='';
			}
			if(!this.vehicle.model){
				this.modelColor='border-color: red';
			}else{
				this.modelColor='';
			}
			if(!this.vehicle.price){
				this.priceColor='border-color: red';
			}else{
				this.priceColor='';
			}
			if(!this.vehicle.type){
				this.typeColor='border-color: red';
			}else{
				this.typeColor='';
			}
			if(!this.vehicle.category){
				this.categoryColor='border-color: red';
			}else{
				this.categoryColor='';
			}
			if(!this.vehicle.fuelType){
				this.fuelTypeColor='border-color: red';
			}else{
				this.fuelTypeColor='';
			}
			if(!this.vehicle.consumption){
				this.consumptionColor='border-color: red';
			}else{
				this.consumptionColor='';
			}
			if(!this.vehicle.doorNumber){
				this.doorNumberColor='border-color: red';
			}else{
				this.doorNumberColor='';
			}
			if(!this.vehicle.peopleNumber){
				this.peopleNumberColor='border-color: red';
			}else{
				this.peopleNumberColor='';
			}
			if(!this.vehicle.picture){
				this.pictureColor='border-color: red';
			}else{
				this.pictureColor='';
			}
			
			if(!this.vehicle.brand || !this.vehicle.model || !this.vehicle.price || !this.vehicle.type
			   || !this.vehicle.category || !this.vehicle.fuelType || !this.vehicle.consumption || !this.vehicle.doorNumber
			   || !this.vehicle.peopleNumber || !this.vehicle.picture){
				this.errorMessage='All fields are neccessary!';
				this.errorColor = "color:red";
				return;
			}
			
			this.errorMessage = '';
		
			axios.put('rest/vehicles/update/' + this.vehicle.id, this.vehicle)
			    .then(response => {
					this.vehicle = response.data;
					router.push("/managersObjects");

			    });
		}
	}
	
});