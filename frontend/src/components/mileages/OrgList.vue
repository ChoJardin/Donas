<template>
  <div>
    <div id="charity-main" >
      <div id="charity-wrap">
        <div id="charity-profile">
          <img id="charity-image" :src="`${charity.picture}`">
          <div id="charity-text">
            <div>
              <h1 id="charity-title">{{charity.name}}</h1>
              <div id="charity-description">{{ currencyString }} 마일리지 모금</div></div>
            <div>
              <div class="charity-tag">
                <span class="charity-set" style="margin-right: 3px">{{charity.tag}}</span>
                <span class="charity-set">{{charity.tag2}}</span>
              </div>
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
    padding: 7px;
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

.charity-tag {
  display: flex;
  margin-top: 10px;
  font-size: 0.8em;
  text-align: center;
  width: 100%;
  margin-left: 15px;
}

.charity-set {
  border: #183a1d solid 1px;
  padding: 2px 5px;
  border-radius: 50em;
}
</style>