<template>
    <div>
        <receipt-search v-on:select="selectReceipt"></receipt-search>
        <ul class="list-group padding" v-if="receipt && receipt.refunded>0" >
          <h5 class="card-title">환불 내역</h5>
          <div class = "row">
              <div class = "col-1">id</div>
              <div class = "col">날짜</div>
              <div class = "col">총환불액</div>
              <div class = "col">현금</div>
              <div class = "col">카드</div>
              <div class = "col">이체</div>
          </div>
          <li class="list-group-item"
                  v-for="(r, index) in receipt.refunds"
                  :key="index"
              >
                  <div class = "row">
                      <div class = "col-1"><label><small>{{ r.id }}</small></label></div>
                      <div class = "col">{{ r.date }}</div>
                      <div class = "col">₩{{ r.total }}</div>
                      <div class = "col">₩{{ r.cash }}</div>
                      <div class = "col">₩{{ r.credit }}</div>
                      <div class = "col">₩{{r.transfer }}</div>
                      <button class="btn btn-primary" type="button">재수납</button>
                  </div>
              </li>
              
          </ul>
          <div v-else-if="receipt && receipt.refunded==0" class="d-grid gap-2">
            <button class="btn btn-primary" type="button">환불</button>
          </div>
  </div>
</template>

<script>
import refundDS from '../service/RefundDataService';
import invoiceDS from '../service/InvoiceDataService';
import receiptDS from '../service/ReceiptDataService';
import SearchReceipt from './SearchReceipt';
export default {
    components: {
      'receipt-search' : SearchReceipt
    },
  name: "refund-add",
  data() {
    return {
      receipt: null,
      submitted: false,
      refund: {
        total : null
      }
    };
  },
  methods: {
    selectReceipt(r) {
        this.receipt = r;
        this.refund.total = this.receipt.amount;
    },
    addRefund() {
      //1) create refund invoice
      //2) create refund 
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
            this.submitted = true;
        })
        .catch(e => {
          console.log(e);
        });
    },
    addReceipt() {
      //1) create refund invoice
      //2) create receipt
      var data = {
        pid: this.receipt.pid,
        rid: this.receipt.id,
        cash: this.refund.cash,
        credit: this.refund.credit,
        transfer: this.refund.transfer
      };
      console.log(data);
      refundDS.addRefund(JSON.parse(JSON.stringify(data)))
        .then(() => {
            console.log('saved');
            this.submitted = true;
        })
        .catch(e => {
          console.log(e);
        });
    },
    newRefund() {
      this.submitted = false;
      this.invoice = null;
    }
  }
};
</script>

<style>
.padding{
  padding-top: 50px;
}
</style>