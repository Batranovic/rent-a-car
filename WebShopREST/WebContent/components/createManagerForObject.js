Vue.component("managerForObject", {
	data: function(){
		return{

			manager: {name: null, surname: null, gender: null, birthday: null, username: null, password: null },

			nameColor : '',
			surnameColor: '',
			genderColor: '',
			birthdayColor: '',
			usernameColor : '',
			passwordColor : '',
			errorMessage: '',
			errorColor: '',
		}
	},
	
	template: `
		<div class="container">
            <h1>Register manager</h1>
            <form class="form-table">
            <tr>
            	<td><label>Name: </label></td>
            	<td><input type="text" v-model="manager.name" v-bind:style="nameColor"></td>
            </tr>
            <br>
             <tr>
            	<td><label>Surname: </label></td>
            	<td><input type="text" v-model="manager.surname" v-bind:style="surnameColor"></td>
            </tr>
            <br>
              <tr>
            	<td><label>Gender: </label></td>
             <select name="genderSelect" v-model="manager.gender" v-bind:style="genderColor" style="width: 150px;">
                    <option value="M">male</option>
                    <option value="F">female</option>
                </select><br>
            </tr>
            <br>
             <tr>
            	<td><label>Date of birth: </label></td>
            	<td><input type="date" v-model="manager.birthday" v-bind:style="birthdayColor"></td>
            </tr>
            <br>
              <tr>
            	<td><label>Username: </label></td>
            	<td><input type="text" v-model="manager.username" v-bind:style="usernameColor"></td>
            </tr>
            <br>
             <tr>
            	<td><label>Password: </label></td>
            	<td><input type="password" v-model="manager.password" v-bind:style="passwordColor"></td>
            </tr>
            <br>
            <div class="form-row">
                <button  type='button' v-on:click="createManager()">Register</button><br>
                <h5 v-bind:style="errorColor">{{errorMessage}}</h5>
             </div>
            </form>
        </div>
	
	`,
	
	mounted(){
		if(localStorage.getItem("createdRentACarObject") == null) {
			this.errorMessage="You cannot create manager";
			this.errorColor = "red";
		}
	}, 
	
	methods: {
		createManager: function(){
			event.preventDefault();
			if(!this.manager.name){
				this.nameColor='border-color: red';
			}else{
				this.nameColor='';
			}
			if(!this.manager.surname){
				this.surnameColor='border-color: red';
			}else{
				this.surnameColor='';
			}
			if(!this.manager.gender){
				this.genderColor='border-color: red';
			}else{
				this.genderColor='';
			}
			if(!this.manager.birthday){
				this.birthdayColor='border-color: red';
			}else{
				this.birthdayColor='';
			}
			if(!this.manager.username){
				this.usernameColor='border-color: red';

			}else{
				this.usernameColor='';
			}
			if(!this.manager.password){
				this.passwordColor='border-color: red';
				return;
			}else{
				this.passwordColor='';
			}
			
			if(!this.manager.name || !this.manager.surname || !this.manager.gender || !this.manager.birthday || !this.manager.username || !this.manager.password){
				this.errorMessage='All fields are neccessary!';
				this.errorColor = "color:red";
				return;
			}
			
			this.errorMessage = '';
		
			axios.post('rest/users/createManager', this.manager)
			    .then(response => {
					const registeredManager = response.data;
			        const createdObject = JSON.parse(localStorage.getItem("createdRentACarObject"));
			        createdObject.managerId = registeredManager.id;
			        axios.post('rest/rentACarObjects/', createdObject)
				    .then(response => {
						const a = response.data;
				        router.push(`/viewRentACarObject`);
				        localStorage.removeItem("createdRentACarObject");
				    });	
			    });
		}
	}
	
});