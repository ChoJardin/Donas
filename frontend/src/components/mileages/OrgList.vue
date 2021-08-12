<template>
  <div>
    <div id="charity-main" >
      <div id="charity-wrap">
        <div id="charity-profile">
          <img id="charity-image" src="../../assets/donut1.png">
          <div id="charity-text">
            <div>
              <h1 id="charity-title">{{charity.name}}</h1>
              <div id="charity-description">{{ charity.total }}원 모금</div></div>
            <div>
              <div id="charity-tag">{{charity.tag}}</div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: "OrgList",
  props: {
    charity: Object
  }
}
</script>

<style scoped>
#charity-main {
    height: 120px;
    width: 90%;
    margin: 15px 5%;
    display: flex;
    flex-direction: column;
    align-items: center;

    /*border: 1px solid #292929;*/
    border-radius: 30px;
    box-shadow: 0 0 15px -7px #292929;
}

#charity-wrap {
  width: 280px;
}

/*게시글 작성 정보*/
#charity-profile {
    display: flex;
    padding-top: 10px;
    /*width: 100%;*/
}

#charity-image {
    height: 100px;
    width: 110px;
    border: 1px solid #292929;
    border-radius: 50%;
    margin-bottom: 2px;
    /*margin-left: 15px;*/
    /*margin-top: 15px;*/
}

#charity-text {
    /*flex-direction: column;*/

    justify-content: center;

}

#charity-title {
  margin-left: 15px;
  margin-top: 13px;
  display: flex;
  justify-items: start;
  font-weight: bold;
  font-size: 1.0em;
}

#charity-description {
  margin-top: 10px;
  margin-left: 15px;
  /*margin-top: 13px;*/
  display: flex;
  font-size: 0.9em;

}

#charity-tag {
  display: flex;
  margin-top: 15px;
  font-size: 0.8em;
  text-align: center;
  border: #183a1d solid;

}
</style>