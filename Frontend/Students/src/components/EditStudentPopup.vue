<script setup>
  import { defineProps, defineEmits } from "vue"

  const props = defineProps({
    student: {
      type: Object,
      required: true,
    },
  })

  const emit = defineEmits(["save", "cancel"])

  const save = () => {
    emit("save", { ...props.student })
  }

  const cancel = () => {
    emit("cancel")
  }
</script>

<template>
    <div class="bg-white p-6 rounded-lg shadow-2xl max-w-md w-full">
      <h2 class="text-2xl font-bold text-rich-black mb-4">Edit Student</h2>
      <input v-model="student.firstName" type="text" placeholder="First Name" class="mt-3 p-3 rounded border border-gray-300 w-full focus:outline-none focus:ring-2 focus:ring-luxury-gold focus:border-transparent" />
      <input v-model="student.lastName" type="text" placeholder="Last Name" class="mt-3 p-3 rounded border border-gray-300 w-full focus:outline-none focus:ring-2 focus:ring-luxury-gold focus:border-transparent" />
      <input v-model="student.age" type="number" placeholder="Age" class="mt-3 p-3 rounded border border-gray-300 w-full focus:outline-none focus:ring-2 focus:ring-luxury-gold focus:border-transparent" />
      <select v-model="student.gender" class="mt-3 p-3 rounded border border-gray-300 w-full focus:outline-none focus:ring-2 focus:ring-luxury-gold focus:border-transparent">
        <option value="MALE">Male</option>
        <option value="FEMALE">Female</option>
      </select>
      <div class="flex justify-between mt-6">
        <button @click="save" class="bg-green-500 text-white p-2 px-4 rounded-lg font-semibold hover:bg-green-600 transition">Save</button>
        <button @click="cancel" class="bg-red-500 text-white p-2 rounded-lg font-semibold hover:bg-red-600 transition">Cancel</button>
      </div>
    </div>
  </template>
