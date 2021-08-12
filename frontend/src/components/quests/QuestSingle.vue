  <template>
  <div>
    <div id="single-quest-main">
      <div id="single-quest-wrap">

        <!--게시글 작성 정보-->
        <div id="single-quest-profile">
          <img id="quest-image" src="../../assets/donut1.png">
          <div id="single-quest-text">
            <h1 id="single-quest-title">{{ quest.title }}</h1>
            <div id="single-quest-description">{{quest.description}}</div>
          </div>
        </div>
        <!--게시글 작성 정보-->
      </div><!--single-article-wrap ends-->
    </div><!--single-article-main ends-->
  </div>
</template>

<script>

export default {
  name: "QuestList",
  props: {
    quest: Object,
  },

}
</script>

<style scoped>
#single-quest-main {
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

#single-quest-wrap {
  width: 280px;
}

/*게시글 작성 정보*/
#single-quest-profile {
    display: flex;
    padding-top: 10px;
    width: 100%;
}

#quest-image {
    height: 100px;
    width: 110px;
    border: 1px solid #292929;
    border-radius: 50%;
    margin-bottom: 2px;
    /*margin-left: 15px;*/
    /*margin-top: 15px;*/
}

#single-quest-text {
    /*flex-direction: column;*/
    margin-left: 15px;
    justify-content: center;
    margin-top: 13px
}

#single-quest-title {
  display: flex;
  justify-items: start;
  font-weight: bold;
  font-size: 1.0em;
}

#single-quest-description {
  margin-top: 20px;
  display: flex;
  font-size: 0.8em;

}

</style>