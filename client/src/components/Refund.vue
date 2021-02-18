<template>
    <div>
      <receipt-search v-on:select="selectReceipt"></receipt-search>
      <div v-if="receipt && receipt.refunded<receipt.amount" class="d-grid gap-2 padding">
        <div class = "row">
              <div class = "col">총환불액</div>
              <div class = "col">현금</div>
              <div class = "col">카드</div>
              <div class = "col">이체</div>
        </div>
        <div class = "row">
              <div class = "col">{{refund.total}}</div>
              <div class = "col">
              <input
                  type="number"
                  class="form-control"
                  id="cash"
                  required
                  v-model="refund.cash"
                  name="cash"
                  v-on:keyup="typing" 

                />
              </div>
              <div class = "col">
                <input
                  type="number"
                  class="form-control"
                  id="credit"
                  required
                  v-model="refund.credit"
                  name="credit"
                  v-on:keyup="typing" 
                />
              </div>
              <div class = "col">
                <input
                  type="number"
                  class="form-control"
                  id="transfer"
                  required
                  v-model="refund.transfer"
                  name="transfer"
                  v-on:keyup="typing" 
                />
              </div>
        </div>
        <b-button  block variant="danger"
        :class="{disabled: refund.total > receipt.amount-receipt.refunded}"
        @click="addRefund"
        >환불하기</b-button>
      </div>
        <ul class="list-group padding" v-if="receipt && receipt.refunded>0" >
          <h5 class="card-title">환불 내역</h5>
          <div class = "row">
              <div class = "col">날짜</div>
              <div class = "col">총환불액</div>
              <div class = "col">현금</div>
              <div class = "col">카드</div>
              <div class = "col">이체</div>
              <div class = "col"></div>
          </div>
          <li class="list-group-item"
                  v-for="(r, index) in receipt.refunds"
                  :key="index"
              >
                  <div class = "row">
                      <div class = "col">{{ r.date }}</div>
                      <div class = "col">₩{{ r.total }}</div>
                      <div class = "col">₩{{ r.cash }}</div>
                      <div class = "col">₩{{ r.credit }}</div>
                      <div class = "col">₩{{r.transfer }}</div>
                      <div class = "col" v-if="r.cancelledTotal == r.total">재수납 완료</div>
                      <div v-else class="col">
                          <button 
                          @click="showModal(index)"
                          class="btn btn-outline-secondary" 
                          type="button" >재수납</button>
                      </div>
                  </div>
              </li>
          </ul>
      <div>
        <b-modal ref="modal" hide-footer title="재수납하기">
          <div class="form-group">
                <label for="cancel">재수납 최대 금액 {{refunded.total - refunded.cancelledTotal}} 입니다</label>
                <input
                  type="number"
                  class="form-control"
                  id="cancel"
                  required
                  v-model="cancel"
                  name="cancel"
                />
          </div>
          <b-button pill variant="outline-secondary"
          :class="{disabled: cancel > refunded.total - refunded.cancelledTotal}"
          @click="cancelRefund"
          >수납하기</b-button>
          <b-button pill variant="outline-danger" @click="hideModal">닫기</b-button>


        </b-modal>
      </div>
      

  </div>
</template>

<script>
/*<cancel-refund  v-bind="refunded" v-on:close="closeModal"></cancel-refund>*/
import refundDS from '../service/RefundDataService';
import SearchReceipt from './SearchReceipt';
import CancelRefund from './CancelRefund';
export default {
    components: {
      'receipt-search' : SearchReceipt,
      'cancel-refund' : CancelRefund
    },
  name: "refund-add",
  data() {
    return {
      receipt: {
        amount: null,
        refunded: null,
        refunds: []
      },
      submitted: false,
      //add new refund 
      refund: {
        cash: 0,
        credit: 0,
        transfer: 0,
        total: 0
      },
      //for cancel refund
      currentIndex : -1,
      refunded : {
        total:null,
        cancelledTotal:null
      },
      cancel : null
    };
  },
  methods: {
    selectReceipt(r) {
      console.log(r);
        this.receipt = r;
    },
    typing: function(e) {
      if (e) this.refund.total = Number(this.refund.cash) + Number(this.refund.credit) + Number(this.refund.transfer);
    },
    hideModal() {
        this.$refs['modal'].hide()
        this.receipt.refunds[this.currentIndex] = this.refunded;
        this.refunded = {
          total : null,
          cancelledTotal : null
        };
        this.currentIndex = -1;
    },
    showModal(index) {
      this.currentIndex = index;
      this.refunded = this.receipt.refunds[index];
      this.cancel = this.refunded.total - this.refunded.cancelledTotal;//initialize input
      this.$refs['modal'].show();
    },
    addRefund() {
      console.log('add refund() called');
        var data = {
          pid: this.receipt.pid,
          rid: this.receipt.id,
          cash: this.refund.cash,
          credit: this.refund.credit,
          transfer: this.refund.transfer
        };
        refundDS.addRefund(JSON.parse(JSON.stringify(data)))
        .then(() => {
            console.log('saved');
            this.$router.go();
        })
        .catch(e => {
          console.log(e);
        });
    },
    cancelRefund() {
      if(this.refunded && this.cancel <= this.refunded.total - this.refunded.cancelledTotal) {
        refundDS.cancelRefund(this.refunded.id, this.cancel)
      .then(() => {
        console.log('went through');
        this.$router.go();
      })
      .catch(e=> {
        console.log(e);
      });
      }

    }
  }
};
</script>

<style>
.padding{
  padding-top: 50px;
  padding-bottom: 50px;
}
</style>