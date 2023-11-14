import axios from "axios";
import Swal from "sweetalert2";

export default class TodoService {

  // API Url adress
  url = "http://localhost:8080/api";

  //data getall
  async getAll() {
    return await axios.get(this.url+"/todos/getall");
  }

  async completedUpdate(id) {
    return await axios
      .post(
        this.url+"/todos/iscomletedupdate/{id}?todoId=" + id
      )
      .then((res) => {
        getResponseAlert(res);
      })
      .catch((err) => {
        getErrorAlert();
      });
  }

  async getCompletedTodos() {
    return await axios.get(this.url+"/todos/completedtodos");
  }

  async addTodo(todo) {
    await axios
      .post(this.url+"/todos/add", todo)
      .then((res) => {
        getResponseAlert(res);
      })
      .catch((err) => {
        getErrorAlert();
      });
  }

  async removeTodo(id) {
    await axios
      .post(this.url+"/todos/remove/{id}?id=" + id)
      .then((res) => {
        getResponseAlert(res);
      })
      .catch((err) => {
        getErrorAlert();
      });
  }

  async updateTodo(id, todo) {
    await axios
      .put(this.url+"/todos/update/{id}?id=" + id, todo)
      .then((res) => {
        getResponseAlert(res);
      })
      .catch((err) => {
        getErrorAlert();
      });
  }

  async getTodos() {
    return await axios.get(this.url+"/todos/gettodos");
  }

  async getByTodo(id) {
    return await axios.get(
      this.url+"/todos/getbyid/{id}?id=" + id
    );
  }

  async deleteDoneTasks() {
    await axios
      .post(this.url+"/todos/deletedonetasks")
      .then((res) => {
        getResponseAlert(res);
      })
      .catch((err) => {
        getErrorAlert();
      });
  }

  async deleteAllTasks() {
    await axios
      .post(this.url+"/todos/deletealltasks")
      .then((res) => {
        getResponseAlert(res);
      })
      .catch((err) => {
        getErrorAlert();
      });
  }
}

function getResponseAlert(res) {
  return res.data.success
    ? Swal.fire({
        title: "Success!",
        text: res.data.message,
        icon: "success",
        confirmButtonText: "Okey",
      })
    : Swal.fire({
        title: "Error!",
        text: res.data.message,
        icon: "error",
        confirmButtonText: "Okey",
      });
}

function getErrorAlert() {
  Swal.fire({
    title: "Error!",
    text: "An error occurred.",
    icon: "error",
    confirmButtonText: "Okey",
  });
}
