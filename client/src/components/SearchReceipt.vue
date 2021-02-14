<template>
    <div>
        <name-search v-on:select="selectPerson"></name-search>
        <ul class="list-group" v-if="receipts.length>0" >
            <h5>수납 내역</h5>
            <div class = "row">
                    <div class = "col-1">id</div>
                    <div class = "col">날짜</div>
                    <div class = "col">총진료비</div>
                    <div class = "col">환자부담금</div>
                    <div class = "col">누적 수납</div>
                    <div class = "col">수납</div>
                    <div class = "col">환불</div>
            </div>
           <li class="list-group-item"
                :class="{ active: index == currentIndex }"
                v-for="(r, index) in receipts"
                :key="index"
                @click="setReceiptActive(index)"
            >
                <div class = "row">
                    <div class = "col-1"><label><small>{{ r.id }}</small></label></div>
                    <div class = "col">{{ r.date }}</div>
                    <div class = "col">₩{{ r.total }}</div>
                    <div class = "col">₩{{ r.outOfPocket }}</div>
                    <div class = "col">₩{{ r.paid }}</div>
                    <div class = "col">₩{{r.amount }}</div>
                    <div class = "col">₩{{r.refunded }}</div>
                </div>
            </li>
        </ul>
        <div class="card-body" v-if="currentIndex >-1 && receipts[currentIndex].refunded>0" >
        <h5 class="card-title">환불 내역</h5>
        <div class = "row">
            <div class = "col-1">id</div>
            <div class = "col">날짜</div>
            <div class = "col">총환불액</div>
            <div class = "col">현금</div>
            <div class = "col">카드</div>
            <div class = "col">이체</div>
        </div>
        <div class = "row">
            <div class = "col-1"><label><small>{{ receipts[currentIndex].id }}</small></label></div>
            <div class = "col">{{ receipts[currentIndex].refundDate }}</div>
            <div class = "col">₩{{ receipts[currentIndex].refundTotal }}</div>
            <div class = "col">₩{{ receipts[currentIndex].refundCash }}</div>
            <div class = "col">₩{{ receipts[currentIndex].refundCredit }}</div>
            <div class = "col">₩{{receipts[currentIndex].refundTransfer }}</div>
        </div>
        </div>
    </div>
</template>

<script>
import receiptDS from '../service/ReceiptDataService';
import SearchPerson from "./SearchPerson"
export default {
    components: {
      'name-search' : SearchPerson
    },
  name: "invoice-search",
  data() {
    return {
      receipts: [],
      currentIndex: -1,
      person: null
    };
  },
  methods: {
    selectPerson(p) {
        this.person = p;
        if(this.person) {
            this.currentIndex = -1;
            receiptDS.getAllReceiptsByPid(this.person.id)
                .then(res => {
                    this.receipts =res.data
                })
                .catch(e => {
                    console.log(e);
                })
        }
    },
    setReceiptActive(index) {
        this.currentIndex = index;
        this.$emit('select', this.receipts[this.currentIndex]);
    }
  }
};
</script>

<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}
</style>
