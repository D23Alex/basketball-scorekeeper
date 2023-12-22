<script setup>

import Header from "./components/Header.vue";
import Footer from "./components/Footer.vue";
</script>

<template>
  <Header/>
  <router-view></router-view>
  <Footer/>
</template>

<style>
  
  body {
    background-color: #ECE2D0;
    margin: 0;
    padding: 0;
  }

  header {
    padding: 20px;
    margin: 0;
    background-color: #004777;
    
    height: 2.5vh;
    width: 100%;
    

    /* position: sticky; */
  }

  header a {
    float: left;
    text-align: center;
    padding: 12px;

    color: #FFF;
    text-decoration: none;
    font-size: 18px;
    line-height: 25px;
    border-radius: 4px;
  }
</style>
