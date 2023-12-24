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
    padding: 10px;
    margin: 0; 

    background-color: #004777;
    
    height: 10%;
    width: 100%;
    justify-content: center;
  }

  header a { 
    /* кнопки в header */
    text-align: center;
    padding: 12px;
    
    color: #FFF;
    text-decoration: none;
    font-size: 18px;
    line-height: 25px;
    border-radius: 4px;
  }

</style>
