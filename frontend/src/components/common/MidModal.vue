<template>
    <transition name="mid-modal">
      <div class="mid-modal-mask">
        <div class="mid-modal-wrapper">
          <div class="mid-modal-container">

            <div class="mid-modal-header">
              <slot name="header">
                default he
              </slot>
              <i class="material-icons" @click="$emit('close')">close</i>
            </div>

            <div class="mid-modal-body">
              <slot name="opt1">
                default body
              </slot>
            </div>


<!--          <div class="mid-modal-container">-->
<!--            <div class="mid-modal-footer">-->
<!--              <slot name="opt3">-->
<!--                default body-->
<!--              </slot>-->
<!--            </div>-->
<!--          </div>-->
            <!--<div class="mid-modal-footer">-->
            <!--  <slot name="footer">-->
            <!--    default footer-->
            <!--    <button class="mid-modal-default-button" @click="$emit('close')">-->
            <!--      OK-->
            <!--    </button>-->
            <!--  </slot>-->
            <!--</div>-->
        </div>
        </div>
      </div>
    </transition>
</template>

<script>
export default {
  name: "MidModal"
}
</script>

<style scoped>
.mid-modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  right: 0;
  margin: 0 auto;
  width: 100%;
  max-width: 425px;
  height: 100%;
  background-color: rgba(0, 0, 0, .5);
  display: table;
  transition: opacity .3s ease;
}

.mid-modal-wrapper {
  display: table-cell;
  vertical-align: middle;
  position: absolute;
  top: 20%;
  left: calc((100% - 300px) / 2);
}

.mid-modal-container {
  width: 300px;
  margin: 0px auto;
  padding: 20px 20px;
  background-color: rgba(255, 255, 255);
  /*border-top-left-radius: 30px;*/
  /*border-top-right-radius: 30px;*/
  border-radius: 20px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
  transition: all .3s ease;
  font-family: GongGothicLight;
  height: 500px;
  overflow-y: scroll;
  /*font-family: Helvetica, Arial, sans-serif;*/
}

.mid-modal-header {
  color: #000302;
  width: 100%;
  height: 24px;
  position: relative;
  display: flex;
  align-items: center;
}

.mid-modal-header .material-icons {
  position: absolute;
  float: right;
  top: 0;
  right: 0;
  /*flex: 1 1 0;*/
}


.mid-modal-body button {
  padding: 20px;
}

.mid-modal-body {
  padding: 20px 0;
  /*border-bottom: 1px solid #a0a0a0;*/
}

.mid-modal-footer {
  padding: 20px
}

.mid-modal-default-button {
  float: right;
}

/*
 * The following styles are auto-applied to elements with
 * transition="modal" when their visibility is toggled
 * by Vue.js.
 *
 * You can easily play with the modal transition by editing
 * these styles.
 */

.mid-modal-enter {
  opacity: 0;
}

.mid-modal-leave-active {
  opacity: 0;
}

.mid-modal-enter .mid-mid-modal-container,
.mid-modal-leave-active .mid-modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}

/*.modal-enter {*/
/*  opacity: 0;*/
/*  transform: translateY(100%);*/
/*}*/

/*.modal-enter-active {*/
/*  transition: all .5s ease;*/
/*}*/

/*.modal-leave-active {*/
/*  transition: all .5s ease;*/
/*}*/

/*.modal-enter-from,*/
/*.modal-leave-to {*/
/*  opacity: 0;*/
/*  transform: translateY(100%);*/
/*}*/

</style>