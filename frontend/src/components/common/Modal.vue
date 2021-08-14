<template>
    <transition name="modal">
      <div class="modal-mask">
        <div class="modal-wrapper">
          <div class="modal-container">

            <!--<div class="modal-header">-->
            <!--  <slot name="header">-->
            <!--    default he-->
            <!--  </slot>-->
            <!--</div>-->

            <div class="modal-body">
              <slot name="opt1">
                default body
              </slot>
            </div>

            <hr>

            <div class="modal-body">
              <slot name="opt2">
                default body
              </slot>
            </div>
          </div>

          <div class="modal-container">
            <div class="modal-footer">
              <slot name="opt3">
                default body
              </slot>
            </div>
          </div>
            <!--<div class="modal-footer">-->
            <!--  <slot name="footer">-->
            <!--    default footer-->
            <!--    <button class="modal-default-button" @click="$emit('close')">-->
            <!--      OK-->
            <!--    </button>-->
            <!--  </slot>-->
            <!--</div>-->
        </div>
      </div>
    </transition>
</template>

<script>
export default {
  name: "Modal",



}
</script>

<style scoped>
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, .5);
  display: table;
  transition: opacity .3s ease;
}

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
  position: absolute;
  bottom: 0;
  left: calc((100vw - 300px) / 2);
}

.modal-container {
  width: 300px;
  margin: 10px auto;
  padding: 0 30px;
  background-color: #fff;
  /*border-top-left-radius: 30px;*/
  /*border-top-right-radius: 30px;*/
  border-radius: 20px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
  transition: all .3s ease;
  font-family: GongGothicLight;
  /*font-family: Helvetica, Arial, sans-serif;*/
}

.modal-header h3 {
  margin-top: 0;
  color: #42b983;
}

.modal-body button {
  padding: 20px;
}

.modal-body {
  padding: 20px 0;
  /*border-bottom: 1px solid #a0a0a0;*/
}

.modal-footer {
  padding: 20px
}

.modal-default-button {
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

.modal-enter {
  opacity: 0;
}

.modal-leave-active {
  opacity: 0;
}

.modal-enter .modal-container,
.modal-leave-active .modal-container {
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