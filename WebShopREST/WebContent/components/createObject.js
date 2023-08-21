Vue.component("createObject", {
	data: function(){
		return{

			object: {name: null, location: null, workTime: null, logo: null, manager: null},
			freeManagers: [],

			nameColor : '',
			locationColor: '',
			workTimeColor: '',
			logoColor: '',
			managerColor: ''
		}
	},
	
	template: `
		<div class="container">
            <h1>Create new object</h1>
            <form class="form-table">
            <tr>
            	<td><label>Name: </label></td>
            	<td><input type="text" v-model="object.name" v-bind:style="nameColor"></td>
            </tr>
            <br>
             <tr>
            	<td><label>Location: </label></td>
            	<td><input type="text" v-model="object.location" v-bind:style="locationColor"></td>
            </tr>
            <br>
              <tr>
            	<td><label>Work time: </label></td>
            	<td><input type="time" v-model="object.workTime" v-bind:style="workTimeColor"></td>
            </tr>
            <br>
             <tr>
            	<td><label>Logo: </label></td>
            	<td><input type="image" v-model="object.logo" v-bind:style="logoColor"></td>
            </tr>
            </br>
             <br>
              <tr>
            	<td><label>Manager: </label></td>
             <select name="managerSelect" v-model="object.manager" v-bind:style="managerColor" style="width: 150px;">
                    <option v-for="manager in freeManagers" v-bind:value="manager.id">{{manager.name}} {{manager.surname}}</option>
                </select><br>
            </tr>
            <br>
            </form>
        </div>
	
	`,
	
	mounted(){
		axios.get('rest/users/getFreeManagers')
      		.then(response => {
        	this.freeManagers = response.data;
       
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
			}else{
				this.nameColor='';
			}
			if(!this.object.location){
				this.locationColor='border-color: red';
			}else{
				this.locationColor='';
			}
			if(!this.object.workTime){
				this.workTimeColor='border-color: red';
			}else{
				this.workTimeColor='';
			}
			if(!this.object.logo){
				this.logoColor='border-color: red';
			}else{
				this.logoColor='';
			}
			
			if(!this.object.name || !this.object.location || !this.object.workTime || !this.object.logo){
				this.errorMessage='All fields are neccessary!';
				this.errorColor = "color:red";
				return;
			}
			
			this.errorMessage = '';
		
			axios.post('rest/users/createUser', this.user)
			    .then(response => {
					const a = response.data;
			        router.push(`/`);
			    });
		}
	}
	
});