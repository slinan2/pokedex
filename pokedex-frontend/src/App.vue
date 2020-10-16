<template>
  <div id="app">
    <img alt="pokedex" src="https://vignette.wikia.nocookie.net/pokedexpokemon/images/6/66/Pokedex_Kanto.png/revision/latest?cb=20181128020735" style="max-width: 280px">
    <Pokedex msg="Pokedex"/>
  </div>
</template>

<script>
import Pokedex from './components/Pokedex.vue'

export default {
  name: 'App',
  components: {
    Pokedex
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
