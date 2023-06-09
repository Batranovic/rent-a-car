Vue.component("viewRentACarObject", {
  data: function () {
		    return {
		      objects: null
		    }
  },
  template: 
  `
    <div  class="container">
    <center>
  		<h1>All available rent a car objects</h1>
	</center>
	<table class="rentacar-table" border="1">
	<tr bgcolor="lightgrey">
		<th>Name</th>
		<th>Location</th>
		<th>Grade</th>
		<th>Logo</th>
	</tr>
		
	<tr v-for="p in objects" :key="p.id">
		<td>{{p.name }}</td>
		<td>{{p.location }}</td>
		<td>{{p.grade}}</td>
		<td><img :src="p.image" alt="Car Image" width="100"></td>
	</tr>
</table>
</div>
  `,
  mounted() {
    axios
      .get('rentACarObject.txt')
      .then(response => {
        const data = response.data.split("\n");
        const objects = data.map(line => {
          const [id, name, location, grade, image] = line.split(";");
          return {
            id: id.trim(),
            name: name.trim(),
            location: location.trim(),
            grade: grade.trim(),
            image: image.trim()
          };
        });
        this.objects = objects;
      })
      .catch(error => {
        console.error("Error fetching data:", error);
      });
  },
  
  methods:{
	  
  }
});