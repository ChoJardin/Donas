<template>
  <div>
    <div id="charity-main" >
      <div id="charity-wrap">
        <div id="charity-profile">
          <img id="charity-image" :src="`${charity.picture}`">

          <div id="charity-text">
              <div id="charity-title">{{charity.name}}</div>
              <div id="charity-description"><span style="font-family: GongGothicMedium">{{ currencyString }}</span>
                마일리지 모금</div>
              <div class="charity-tag">
                <span class="charity-set" style="margin-right: 3px">{{charity.tag}}</span>
                <span class="charity-set">{{charity.tag2}}</span>
              </div>
          </div>  <!--charity text end -->

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
  },
  computed: {
    currencyString: function (){
      const amount = this.charity.total
      return amount.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
    }
  },
}
</script>

<style scoped>
#charity-main {
    width: 90%;
    margin: 15px 5%;
    display: flex;
    align-items: center;
    border-radius: 30px;
    box-shadow: 0 0 15px -8px #292929;
}


/*게시글 작성 정보*/
#charity-profile {
  width: 100%;
  display: flex;

    /*padding-top: 10px;*/
    /*width: 100%;*/
}

#charity-image {
  height: 80px;
  width: 80px;
  border: 1px solid #183a1d;
  border-radius: 50%;
  padding: 3px;
  /*flex: 1 1 0;*/
  margin: 10px 15px;
}

#charity-text {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  width: 100%;
  /*flex: 3 3 0;*/
  text-align: left;
  margin: 10px;

}

#charity-title {
  /*font-size: 1.1em;*/
  font-family: GongGothicMedium;
  /*font-family: GongGothicBold;*/
  /*margin-left: 15px;*/
  /*margin-top: 13px;*/
  /*display: flex;*/
  /*justify-items: start;*/
  /*font-weight: bold;*/
  /*font-size: 1.0em;*/
}

#charity-description {
  font-size: 0.8em;
  /*margin-top: 10px;*/
  /*margin-left: 15px;*/
  /*margin-top: 13px;*/
  /*display: flex;*/
  /*font-size: 0.9em;*/
}

.charity-tag {
  /*display: flex;*/
  /*margin-top: 10px;*/
  /*font-size: 0.8em;*/
  /*text-align: center;*/
  /*width: 100%;*/
  /*margin-left: 15px;*/
}

.charity-set {
  font-size: 0.8em;
  border: 1px solid #183a1d;
  background-color: #e1eedd;
  padding: 2px 3px;
  border-radius: 15px;
  margin: 5px 5px 5px 0;
}
</style>