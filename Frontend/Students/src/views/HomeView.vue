<script setup>
import { ref, onMounted, computed } from 'vue'
import axios from 'axios'
import AddStudentPopup from '../components/AddStudentPopup.vue'
import EditStudentCard from '../components/EditStudentPopup.vue'

const students = ref([])
const apiBaseUrl = 'http://localhost:8080/students'
const editingStudent = ref(null)
const isEditing = ref(false)
const isAdding = ref(false)
const searchQuery = ref('')

const fetchStudents = async () => {
  const response = await axios.get(apiBaseUrl)
  students.value = response.data
}


const filteredStudents = computed(() => {
  return students.value.filter(student => {
    return (
      student.firstName.toLowerCase().includes(searchQuery.value.toLowerCase()) ||
      student.lastName.toLowerCase().includes(searchQuery.value.toLowerCase())
    )
  })
})

const createStudent = async (student) => {
  await axios.post(apiBaseUrl, student)
  await fetchStudents()
  isAdding.value = false
}

const deleteStudent = async (id) => {
  await axios.delete(`${apiBaseUrl}/${id}`)
  await fetchStudents()
}

const updateStudent = (student) => {
  editingStudent.value = { ...student }
  isEditing.value = true
}

const saveStudent = async (updatedStudent) => {
  await axios.put(`${apiBaseUrl}/${updatedStudent.id}`, updatedStudent)
  await fetchStudents()
  isEditing.value = false
}

const cancelEdit = () => {
  editingStudent.value = null
  isEditing.value = false
}

const closeAddStudent = () => {
  isAdding.value = false
}

const openAddStudent = () => {
  isAdding.value = true
}

onMounted(fetchStudents)
</script>

<template>
  <div class="min-h-screen p-8 text-gray-800 bg-gray-100">
    <h1 class="mb-6 text-3xl font-bold">Student List</h1>

    <!-- Search Bar -->
    <div class="flex mb-6">
      <input 
        type="text" 
        v-model="searchQuery" 
        placeholder="Search" 
        class="w-1/3 px-4 py-2 mr-4 border border-gray-300 rounded"
      />
      <!-- Add Student Button -->
      <button @click="openAddStudent" class="px-4 py-2 text-white transition bg-green-600 rounded hover:bg-green-700">Add New Student</button>
    </div>

    <!-- Add Student Popup -->
    <div v-if="isAdding" class="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
      <AddStudentPopup 
        :isOpen="isAdding" 
        :onClose="closeAddStudent" 
        :onSave="createStudent"
      />
    </div>

    <!-- Student List Table -->
    <div class="overflow-x-auto">
      <table class="min-w-full bg-white border border-gray-300 rounded shadow-md">
        <thead>
          <tr class="bg-gray-200">
            <th class="px-4 py-2 text-left text-gray-600 border-b">First Name</th>
            <th class="px-4 py-2 text-left text-gray-600 border-b">Last Name</th>
            <th class="px-4 py-2 text-left text-gray-600 border-b">Age</th>
            <th class="px-4 py-2 text-left text-gray-600 border-b">Gender</th>
            <th class="px-4 py-2 text-left text-gray-600 border-b">Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="student in filteredStudents" :key="student.id" class="hover:bg-gray-100">
            <td class="px-4 py-2 border-b">{{ student.firstName }}</td>
            <td class="px-4 py-2 border-b">{{ student.lastName }}</td>
            <td class="px-4 py-2 border-b">{{ student.age }}</td>
            <td class="px-4 py-2 border-b">{{ student.gender }}</td>
            <td class="px-4 py-2 border-b">
              <button @click="updateStudent(student)" class="px-3 py-1 text-white transition bg-blue-600 rounded hover:bg-blue-700">Edit</button>
              <button @click="deleteStudent(student.id)" class="px-3 py-1 ml-2 text-white transition bg-red-500 rounded hover:bg-red-600">Delete</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Edit Student Card -->
    <div v-if="isEditing" class="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
      <EditStudentCard 
        :student="editingStudent" 
        @save="saveStudent" 
        @cancel="cancelEdit" 
      />
    </div>
  </div>
</template>
