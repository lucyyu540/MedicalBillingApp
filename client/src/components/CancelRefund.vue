<template> 
  <div>
    <b-modal ref="my-modal" hide-footer title="Using Component Methods">
      <div class="d-block text-center">
        <h3>Hello From My Modal!</h3>
      </div>
      <b-button class="mt-3" variant="outline-danger" block @click="hideModal">Close Me</b-button>
    </b-modal>
  </div>
</template>
<script >
import refundDS from '../service/RefundDataService';
import { BModal, VBModal } from 'bootstrap-vue';
export default {
    components: { BModal },
    directives: {
         'b-modal': VBModal,
    },
mounted: function () {
  this.$nextTick(function () {
    this.$refs['my-modal'].show()
  })
},
  name: "cancel-refund",
  props: {
      refunded: Object
  },
  //:class="{ disabled: r.cancelledTotal == r.total }"
  data() {
    return {
      cancel: null,

    };
  },
  methods: {
      showModal() {
        this.$refs['my-modal'].show()
      },
      hideModal() {
        this.$refs['my-modal'].hide()
      },
    cancelRefund(index) {
      /*
      //chosen refund to cancel
        refunded = this.refund.refunds[index];
      //max possible cancel amount
        this.cancel = refunded.total - refunded.cancelledTotal;
        refundDS.addRefund(this.refund.id, this.cancel)
        .then(() => {
            console.log('saved');
            this.submitted = true;
        })
        .catch(e => {
          console.log(e);
        });
        */
    },
  }
};
</script>

<style>
.padding{
  padding-top: 50px;
  padding-bottom: 50px;
}
</style>