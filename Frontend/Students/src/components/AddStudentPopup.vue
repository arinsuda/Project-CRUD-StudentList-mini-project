<script setup>
import { ref } from 'vue'

const props = defineProps({
  isOpen: Boolean,
  onClose: Function,
  onSave: Function
})

const student = ref({
  firstName: '',
  lastName: '',
  age: '',
  gender: ''
})

const addStudent = () => {
  props.onSave(student.value)
  props.onClose()
  student.value = { firstName: '', lastName: '', age: '', gender: '' }
}

const cancel = () => {
  props.onClose()
  student.value = { firstName: '', lastName: '', age: '', gender: '' }
}
</script>

<template>
    <div class="bg-white p-6 rounded shadow-md max-w-md">
      <h2 class="text-xl font-semibold mb-4">Add New Student</h2>
      <input v-model="student.firstName" type="text" placeholder="First Name" class="p-2 border border-gray-300 rounded w-full focus:outline-none focus:ring" />
      <input v-model="student.lastName" type="text" placeholder="Last Name" class="p-2 border border-gray-300 rounded w-full focus:outline-none focus:ring mt-2" />
      <input v-model="student.age" type="number" placeholder="Age" class="p-2 border border-gray-300 rounded w-full focus:outline-none focus:ring mt-2" />
      <select v-model="student.gender" class="p-2 border border-gray-300 rounded w-full focus:outline-none focus:ring mt-2">
        <option value="" disabled>Select Gender</option>
        <option value="MALE">Male</option>
        <option value="FEMALE">Female</option>
      </select>
      <div class="flex justify-between mt-4">
        <button @click="addStudent" class="bg-blue-600 text-white py-2 px-4 rounded hover:bg-blue-700 transition">Create</button>
        <button @click="cancel" class="bg-gray-300 text-gray-800 py-2 px-4 rounded hover:bg-gray-400 transition">Cancel</button>
      </div>
    </div>
  </template>

  