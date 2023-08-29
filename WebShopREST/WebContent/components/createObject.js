Vue.component("createObject", {
	data: function(){
		return{

			object: {name: null, address: null, longitude: null, latitude: null, from: null, to: null, logo: null, managerId: null},
			freeManagers: [],
			hasManagers: true,

			nameColor : '',
			addressColor: '',
			longitudeColor: '',
			latitudeColor: '',
			fromColor: '',
			toColor: '',
			logoColor: '',
			managerColor: '',
			errorMessage: '',
			errorMessages: ''
		}
	},
	
	template: `
		<div class="container">
            <h1>Create new object</h1>
            <form class="form-table">
            <tr>
            	<td><label>Name: </label></td>
            	<td>
            		<input type="text" v-model="object.name" v-bind:style="nameColor">
            		<p v-if="showErrorMessages" style="color: red; font-size: 13px;">Field cannot contain numbers</p>
            	</td>
            </tr>
            <br>
             <tr>
            	<td><label>Address: </label></td>
            	<td><input type="text" v-model="object.address" v-bind:style="addressColor"></td>
            </tr>
            <br>
            <tr>
            	<td><label>Longitude: </label></td>
            	<td><input type="text" v-model="object.longitude" v-bind:style="longitudeColor">
            	<p v-if="showErrorMessages" style="color: red; font-size: 13px;">Field cannot contain letters</p>
            	</td>
            </tr>
            <br>
            <tr>
            	<td><label>Latitude: </label></td>
            	<td><input type="text" v-model="object.latitude" v-bind:style="latitudeColor">
            	<p v-if="showErrorMessages" style="color: red; font-size: 13px;">Field cannot contain letters</p>
            	</td>
            </tr>
            <br>
              <tr>
			    <td><label>From: </label></td>
			    <td><input type="time" v-model="object.from" v-bind:style="fromColor"></td>
			</tr>
			<tr>
			    <td><label>To: </label></td>
			    <td><input type="time" v-model="object.to" v-bind:style="toColor"></td>
			</tr>
            <br>
             <tr>
            	<td><label>Logo: </label></td>
            	<td><input type="file" v-model="object.logo" v-bind:style="logoColor"></td>
            </tr>
            </br>
             <br>
              <tr>
            	<td><label>Manager: </label></td>
             <select name="managerSelect" v-model="object.managerId" v-bind:style="managerColor" style="width: 150px;">
                    <option v-for="manager in freeManagers" v-bind:value="manager.id">{{manager.name}} {{manager.surname}}</option>
             </select><br>
             <label v-if="!hasManagers" style="color:red"> No free managers, </br>register manager on next page</label>
             
            </tr>
            <br>
            <div class="form-row">
                <button v-on:click="create()">Create</button><br>
                <h5 v-bind:style="errorColor">{{errorMessage}}</h5>
             </div>
            </form>
        </div>
	
	`,
	
	mounted(){
		axios.get('rest/users/getFreeManagers')
      		.then(response => {
        	this.freeManagers = response.data;
        	this.hasManagers = this.freeManagers.length !== 0;
        	
      })
      .catch(error => {
        console.error("Error fetching data:", error);
      });
		
	}, 
	
	methods: {
		create: function(){
			event.preventDefault();
			if(!this.object.name){
				this.nameColor='border-color: red';
				this.showErrorMessages = false;
			}else if (/\d/.test(this.object.name)) {
		        this.nameColor = 'border-color: red';
		        this.showErrorMessages = true;
			}else{
				this.nameColor='';
				this.showErrorMessages = false;
			}
			if(!this.object.address){
				this.addressColor='border-color: red';
			}else{
				this.addressColor='';
			}
			if(!this.object.longitude){
				this.longitudeColor='border-color: red';
				this.showErrorMessages = false;
			}else if (/[a-zA-Z]/.test(this.object.longitude)) {
		        this.longitudeColor = 'border-color: red';
		        this.showErrorMessages = true;
			}else{
				this.longitudeColor='';
				this.showErrorMessages = false;
			}
			if(!this.object.latitude){
				this.latitudeColor='border-color: red';
				this.showErrorMessages = false;
			}else if (/[a-zA-Z]/.test(this.object.latitude)) {
		        this.latitudeColor = 'border-color: red';
		        this.showErrorMessages = true;
			}else{
				this.latitudeColor='';
				this.showErrorMessages = false;
			}
			if(!this.object.from){
				this.fromColor='border-color: red';
			}else{
				this.fromColor='';
			}
			if(!this.object.to){
				this.toColor='border-color: red';
			}else{
				this.toColor='';
			}
			if(!this.object.logo){
				this.logoColor='border-color: red';
			}else{
				this.logoColor='';
			}
			if(this.hasManagers && !this.object.managerId){
				this.managerColor='border-color: red';
			}else{
				this.managerColor='';
			}
			
			if(!this.object.name || !this.object.address || !this.object.longitude || !this.object.latitude || !this.object.from || !this.object.to || !this.object.logo || (this.hasManagers && !this.object.managerId)){
				this.errorMessage='All fields are neccessary!';
				this.errorColor = "color:red";
				return;
			}
			
			this.errorMessage = '';
			if(this.hasManagers){
				axios.post('rest/rentACarObjects/', this.object)
				    .then(response => {
						const a = response.data;
				        router.push(`/viewRentACarObject`);
				    });				
			}else{
				localStorage.setItem("createdRentACarObject", JSON.stringify(this.object));
				router.push(`/managerForObject`);
			}
		}
	}
	
});