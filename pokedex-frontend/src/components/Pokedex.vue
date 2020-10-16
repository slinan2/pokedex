<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <p>
      You can search a Pokémon by name or by ID
    </p>
    <input v-model="pokemonId" placeholder="Find Pokémon">
    <br>
    <input type="radio" id="name" value="name" v-model="searchMode">
    <label for="name">By name</label>
    <br>
    <input type="radio" id="id" value="id" v-model="searchMode">
    <label for="id">By id</label>
    <br>
    <button @click="searchPokemon">
      Search
    </button>
    <br>
    <div v-if="loading">
      <h3>Loading</h3>
      <img alt="loading" src="https://media.giphy.com/media/lOa0tPKiMLdqVdFiS8/giphy.gif" style="max-width: 280px">
    </div>
    <div v-if="pokemon && pokemon.name">
      <h3>Name: {{ pokemon.name }}</h3>
      <div class="picture stat">
        <img :src="pokemon.picture"/>
      </div>
      <div class="stat">
        <h3>Types</h3>
        <ul>
          <li v-for="type in pokemon.types" :key="type">{{ type }}</li>
        </ul>
      </div>
      <div class="stat">
        <h3>Weight</h3>
        <ul>
          <li>Weight: {{ pokemon.weight }}</li>
        </ul>
      </div>
      <div class="stat">
        <h3>Abilities</h3>
        <ul>
          <li v-for="ability in pokemon.abilities" :key="ability">{{ ability }}</li>
        </ul>
      </div>
      <br>
      <div class="more-button">
        <button @click="viewMore">
          <span v-if="!viewDescription">View more</span>
          <span v-else>View less</span>
        </button>
      </div>
      <div v-if="viewDescription">
        <h3>Description</h3>
        <p class="description">{{ pokemon.description }}</p>
        <h3>Evolutions</h3>
        <ul>
          <li v-for="evolution in pokemon.evolutions" :key="evolution">{{ evolution }}</li>
        </ul>
      </div>
    </div>

  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  data() {
    return {
      searchMode: "name",
      pokemonId: "",
      loading: false,
      viewDescription: false,
      pokemon: {
        name: "",
        picture: "",
        weight: 0,
        abilities: [],
        description: "",
        evolutions: [],
        types: []
      }
    }
  },
  methods: {
    searchById() {
      this.loading = true
      this.pokemon = {}
      axios.get(`api/pokemon/${this.pokemonId}`).then(response => {
        this.pokemon = response.data
      }).catch((error) => {
        this.loading = false
        const code = error.response.status
        if (code === 404) {
          window.alert("Pokemon not found")
        } else if (code === 400) {
          window.alert("The ID should be an integer")
          this.pokemonId = ""
        }
      }).finally(() => this.loading = false)
    },
    searchByName() {
      this.loading = true
      this.pokemon = {}
      axios.get(`api/pokemon?name=${this.pokemonId.toLowerCase()}`).then(response => {
        this.pokemon = response.data
      }).catch((error) => {
        this.loading = false
        const code = error.response.status
        if (code === 404) {
          window.alert("Pokemon not found")
        }
      }).finally(() => this.loading = false)
    },
    searchPokemon() {
      if (!this.pokemonId) {
        window.alert("Please enter an ID or a name")
      } else {
        this.viewDescription = false;
        if (this.searchMode === "id") {
          this.searchById()
        } else {
          this.searchByName()
        }
      }
    },
    viewMore() {
      this.viewDescription = !this.viewDescription;
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

input {
  margin-bottom: 10px;
}

a {
  color: #42b983;
}

.description {
  max-width: 500px;
  display: inline-block;
}

.stat {
  display: inline-block;
  margin-left: 12px;
}

.more-button {
  display: block;
}

</style>
