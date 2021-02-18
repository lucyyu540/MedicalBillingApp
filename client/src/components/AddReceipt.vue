<template>
    <div>
        <invoice-search v-on:select="selectInvoice"></invoice-search>
        <div class="submit-form">
            <div v-if="invoice" class = "padding">
            <div v-if="!submitted">
                <div class="form-group">
                    <label for="total">수납 금액</label>
                    <input
                    type="number"
                    class="form-control"
                    id="amount"
                    required
                    v-model="receipt.amount"
                    name="amount"
                    />
                </div>
                <b-button  block variant="primary"
                :class="{disabled: receipt.amount >invoice.outOfPocket-invoice.paid||receipt.amount == 0}"
                @click="saveReceipt" 
                >수납</b-button>
            </div>
            <div v-else>
                <h4>수납을 완료했습니다</h4>
            </div>
            </div>
        </div>
  </div>
</template>

<script>
import receiptDS from '../service/ReceiptDataService';
import SearchInvoice from './SearchInvoice';
export default {
    components: {
      'invoice-search' : SearchInvoice
    },
  name: "receipt-add",
  data() {
    return {
      receipt: {
        amount: null
      },
      submitted: false,
      invoice: null
    };
  },
  methods: {
    selectInvoice(i) {
        this.invoice = i;
        this.receipt.amount = this.invoice.outOfPocket-this.invoice.paid;
    },
    saveReceipt() {
      var data = {
        pid: this.invoice.pid,
        iid: this.invoice.id,
        amount: this.receipt.amount,
      };
      console.log(data);
      receiptDS.addReceipt(JSON.parse(JSON.stringify(data)))
        .then(() => {
            console.log('saved');
            this.submitted = true;
        })
        .catch(e => {
          console.log(e);
        });
    },
    newReceipt() {
      this.submitted = false;
      this.invoice = null;
      this.receipt =  {
        amount: null
      };
    }
  }
};
</script>

<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}
.padding{
  padding-top: 50px;
  padding-bottom: 50px;
}
</style>