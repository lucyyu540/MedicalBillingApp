<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar-dark bg-dark">
      <router-link to="/" class="navbar-brand">bezKoder</router-link>
      <div class="navbar-nav mr-auto">
        <li class="nav-item">
          <router-link to="/person/add" class="nav-link">환자 등록하기</router-link>
        </li>
         <li class="nav-item">
          <router-link to="/invoice/add" class="nav-link">진료비 청구서 발급</router-link>
        </li>
         <li class="nav-item">
          <router-link to="/receipt" class="nav-link">수납하기</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/receipt" class="nav-link">수납환불</router-link>
        </li>
      </div>
    </nav>

    <div class="container mt-3">
      <router-view />
    </div>
  </div>
</template>

<script>

export default {
  name: 'app'
};
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
