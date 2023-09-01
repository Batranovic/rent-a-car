Vue.component("susUsers", {
	data: function() {
		return {
			users: []
		}
	},
	template:
		`
	   <div class="container">
		<form v-if="users">
		<center>
			<h2><b>Suspicious users</b></h2>
		</center>
			<table border="1">
	            <tr>
	            	<th>Name</th>
	            	<th>Surname</th>
	            	<th>Username</th>
	            	<th>Gender</th>
	            	<th>Birthday</th>
	            	<th>Block</th>
	            </tr>
	            <tr v-for="user in users" :key="user.id">
	            	<td> {{ user.name }}</td>
	            	<td> {{ user.surname }}</td>
	            	<td> {{ user.username }}</td>
	            	<td> {{ user.gender }}</td>
	            	<td> {{ user.birthday }}</td>
	            	<td> <button  v-on:click="blockUser(user.id)">Block</button></td>
	            </tr>
			        
            </table>
		</form>
		</div>
    	`,
	mounted() {
		axios.get('rest/users/getAllSuspiciousUsers', this.users)
			.then(response =>{
				this.users = response.data;
			})

	},
	methods: {
		blockUser: function(id){
			axios.put(`rest/users/blockUser/`+id)
				.then(response => {
					this.users = response.data;
				});
		},
	}
});