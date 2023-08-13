Vue.component("viewRentACarObject", {
  data: function () {
    return {
      searchCriteria: {
        name: null,
        vehicleType: null,
        location: null,
        grade: null
      },
      objects: [], // Array to store all objects from .txt file
      searchResults: [] // Array to store search results
    };
  },
  template: `
    <div class="container">
      <center>
        <h1>All available rent a car objects</h1>
      </center>
          <form>
        <table>
          <tr>
            <td><label>Name:</label></td>
            <td><input type="text" v-model="searchCriteria.name"></td>
            
            <td><label>Vehicle type:</label></td>
            <td><input type="text" v-model="searchCriteria.vehicleType"></td>
            
            <td><label>Location:</label></td>
            <td><input type="text" v-model="searchCriteria.location"></td>
            
            <td><label>Grade:</label></td>
            <td><input type="text" v-model="searchCriteria.grade"></td>
            
            <td><button type="button" v-on:click="search">Search</button></td>
          </tr>
        </table>
      </form>
      <table class="rentacar-table" border="1">
        <tr bgcolor="lightgrey">
          <th>Name</th>
          <th>Location</th>
          <th>Grade</th>
          <th>Logo</th>
        </tr>
        <tr v-for="result in searchResults" :key="result.id">
          <td>{{ result.name }}</td>
          <td>{{ result.location }}</td>
          <td>{{ result.grade }}</td>
          <td><img :src="result.image" alt="Car Image" width="100"></td>
        </tr>
      </table>
    </div>
  `,
  computed: {
    displayResults: function () {
      // If search criteria are empty, display all objects
      if (!this.searchCriteria.name && !this.searchCriteria.vehicleType && !this.searchCriteria.location && !this.searchCriteria.grade) {
        return this.objects;
      }
      // Filter objects based on search criteria
      return this.objects.filter(obj => {
        // Implement your filter logic here
        // You can use obj.name, obj.vehicleType, obj.location, obj.grade
        return (
          obj.name.includes(this.searchCriteria.name || '') &&
          obj.vehicleType.includes(this.searchCriteria.vehicleType || '') &&
          obj.location.includes(this.searchCriteria.location || '') &&
          obj.grade.includes(this.searchCriteria.grade || '')
        );
      });
    }
  },
  methods: {
    search: function () {
      const { name, vehicleType, location, grade } = this.searchCriteria;
      axios
        .get(`/rentACarObjects/searchRentACarObject/${name}/${location}/${vehicleType}/${grade}`)
        .then(response => {
          this.searchResults = response.data;
        })
        .catch(error => {
          console.error("Error searching data:", error);
        });
    }
  },
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
  }
});
