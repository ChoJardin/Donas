  <template>
  <div>
      <v-col cols="12">
            <v-card light>
              <div class="d-flex flex-no-wrap justify-contents-left">
                <div>
                  <v-avatar class="ma-3" size="100" tile>
                    <v-img id="quest-image" src="@/assets/도넛1.png"></v-img>
                  </v-avatar>
                </div>
                <div>
                  <v-card-title><strong>퀘스트 이름</strong></v-card-title>
                  <v-card-subtitle class="pt-1">어떤내용인가??</v-card-subtitle>
                </div>
              </div>
            </v-card>
          </v-col>
<!--      <div id="flex-container">-->
<!--        <hr>-->
<!--        <div id="profile-wrap">-->
<!--          <img id="quest-image" src="@/assets/도넛1.png" alt="">-->
<!--          <div id="profile-info">-->
<!--            <div id="">뱃지</div>-->
<!--          </div>-->
<!--  -->
<!--        </div>-->
<!--      </div>-->
  </div>
</template>

<script>

export default {
  name: "QuestList"

}
</script>

<style scoped>
/*#flex-container {*/
/*  display: flex;*/
/*  flex-direction: column;*/
/*  text-align: start;*/
/*  margin: 15px;*/
/*}*/

/*#profile-wrap {*/
/*  display: flex;*/
/*}*/

/*#quest-image {*/
/*  border: 1px solid #ddd; !* Gray border *!*/
/*  border-radius: 4px;  !* Rounded border *!*/
/*  padding: 5px; !* Some padding *!*/
/*  width: 100px; !* Set a small width *!*/
/*  !*width: 100px;*!*/
/*  !*flex: 1 1 0;*!*/
/*}*/

</style>