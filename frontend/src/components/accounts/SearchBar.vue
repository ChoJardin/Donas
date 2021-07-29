<template>
  <div>
    <div id="searchBar"  style="float: right">
      <input type="text" :class="{searchText:active}" placeholder="Search" />
      <i class="fas fa-search fa-lg" id="searchIcon" @click="active= !active"></i>
    </div>
  </div>
</template>

<script>
export default {
  name: "SearchBar",
  data: function () {
    return {
      active: false
    }
  },

}
</script>

<style scoped>

#searchBar{
  border-radius: 55px;
  padding: 3px;
  background: none;
  display: flex;
  align-items: center;
  /*border: black solid;*/
  margin-right: 15px;

}

#searchIcon {
  width: 15px;
  cursor: pointer;
  padding: 5px 5px;
  margin-right: 2px;

}

input {
  font-size: 8px;
  width: 0px;
  border: 0;
  background: transparent;
  outline: none;
  transition: 1s;
  margin-left: 5px;
}

.searchText {
  width: 90px;
  padding: 5px 10px;
  border: #6cb9a2 solid;

}

</style>