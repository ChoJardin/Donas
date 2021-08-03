<template>
  <transition
      :name="transitionName" :mode="transitionMode" :enter-active-class="transitionEnterActiveClass"
      @before-leave="beforeLeave" @enter="enter" @after-enter="afterEnter">
    <slot/>
  </transition>
</template>

<script>
const DEFAULT_TRANSITION = 'fade'
const DEFAULT_TRANSITION_MODE = 'out-in'

export default {
  name: "TransitionPage",
  // components
  // props
  // data
  data() {
    return {
      prevHeight: 0,
      transitionName: DEFAULT_TRANSITION,
      transitionMode: DEFAULT_TRANSITION_MODE,
      transitionEnterActiveClass: '',
    }
  },
  // methods
  methods: {
    beforeLeave(element) {
      this.prevHeight = getComputedStyle(element).height
    },
    enter(element) {
      const { height } = getComputedStyle(element)
      element.style.height = this.prevHeight
      setTimeout(() => {
        element.style.height = height
      })
    },
    afterEnter(element) {
      element.style.height = 'auto'
    }
  },
  // computed
  // watch
  // lifecycle hook
  created() {
    this.$router.beforeEach((to, from, next) => {
      let transitionName = to.meta.transitionName || from.meta.transitionName || DEFAULT_TRANSITION;

      if (to.meta.transitionName === 'slide') {
        document.body.style.overflow = 'hidden'
      }

      if (from.meta.transitionName === 'slide') {
        document.body.style.overflow = 'null'
      }

      // 피드에서 게시글 상세정보 -> 오른쪽에서 슬라이드
      if (to.name === 'ArticleDetail' || from.name === 'ArticleDetail') {
        transitionName = 'slide-right'
      }

      // 피드에서 프로필 이동 -> 왼쪽 슬라이드
      if (to.name === 'Profile' || from.name === 'Profile') {
        transitionName = 'slide-left'
      }

      //   const toDepth = to.path.split('/').length;
      //   const fromDepth = from.path.split('/').length;
      //   transitionName = toDepth < fromDepth ? 'slide-right' : 'slide-left';
      // }

      this.transitionMode = DEFAULT_TRANSITION_MODE;
      this.transitionEnterActiveClass = `${transitionName}-enter-active`;

      if (to.meta.transitionName === 'zoom') {
        this.transitionMode = 'in-out';
        this.transitionEnterActiveClass = 'zoom-enter-active';
        document.body.style.overflow = 'hidden';
      }

      if (from.meta.transitionName === 'zoom') {
        this.transitionMode = null;
        this.transitionEnterActiveClass = null;
        document.body.style.overflow = null;
      }

      this.transitionName = transitionName;

      next();
    });
  },
}
</script>

<style scoped>
/* fade */
.fade-enter-active,
.fade-leave-active {
  transition-duration: 0.3s;
  transition-property: height, opacity;
  transition-timing-function: ease;
  overflow: hidden;
}

.fade-enter,
.fade-leave-active {
  opacity: 0
}

/* 오른쪽 슬라이드 */
.slide-right-enter-active,
.slide-right-leave-active {
  transition-duration: 0.5s;
  transition-property: height, opacity, transform;
  transition-timing-function: cubic-bezier(0.55, 0, 0.1, 1);
  overflow: hidden;
}

.slide-right-leave-active,
.slide-right-enter {
  opacity: 1;
  transform: translate(100%, 0);
}

/* 왼쪽 슬라이드 */
.slide-left-enter-active,
.slide-left-leave-active {
  transition-duration: 0.5s;
  transition-property: height, opacity, transform;
  transition-timing-function: cubic-bezier(0.55, 0, 0.1, 1);
  overflow: hidden;
}

.slide-left-leave-active,
.slide-left-enter {
  opacity: 1;
  transform: translate(-100%, 0);
}

/* 줌 */
.zoom-enter-active,
.zoom-leave-active {
  animation-duration: 0.5s;
  animation-fill-mode: both;
  animation-name: zoom;
}

.zoom-leave-active {
  animation-direction: reverse;
}

@keyframes zoom {
  from {
    opacity: 0;
    transform: scale3d(0.3, 0.3, 0.3);
  }

  100% {
    opacity: 1;
  }
}
</style>