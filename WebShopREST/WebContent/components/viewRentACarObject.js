Vue.component("viewRentACarObject", {
	data: function(){
		return{
			rentACar : {}
		}
	},
	
	template: `
	<div>
		<table border="1">
			<tr>
				<th>Name</th>
				<th>Location</th>
				<th>Grade</th>
			</tr>
			
			<tr>
				<td>{{rentACar.name}}</td>
				<td>{{rentACar.location}}</td>
				<td>{{rentACar.grade}}</td>
			</tr>
		</table>
	</div>
	`,
	
	mounted(){
		
		
	}, 
	

	
});