Vue.component("user-page", { 
	data: function () {
	    users : null;
	    errorMessage: ''
	},
	    template: 
	    `
	    <div>
				<table border="1">
				<thead>
					<tr colspan="">
						<th>User page</th>
					</tr>
					<tr>
						<th>Name</th>
						<th>Surname</th>
						<th>Gender</th>
						<th>Date of birth</th>
						<th>Username</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="u in users">
						<td>{{u.name}}</td>
						<td>{{u.surname}}</td>
						<td>{{u.gender}}</td>
						<td>{{u.dateOfBirth}}</td>
						<td>{{u.username}}</td>
						<td><button v-on:click="modify(u.id)">Modify</button></td>
					</tr>
				</tbody>
				</table>
			</div>
	    `,
    mounted () {
        axios.get('rest/users/')
				.then(response=>{
					this.users=response.data
				})
    },
    methods: {
    	modify : function(id) {
			router.push(`/userPage/profileModification/` + id)
    	}
    }
});