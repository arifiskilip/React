import React, { useEffect, useState } from "react";
import { Container, FormGroup } from "react-bootstrap";
import TodoService from "../services/todoService";
import * as Yup from "yup";
import { ErrorMessage, Field, Formik, Form } from "formik";

export default function TodoList() {
  const todoService = new TodoService();

  //Todos
  const [todos, setTodos] = useState([]);
  //Update Form
  const [updateForm, setUpdateForm] = useState(false);
  //Update Todo
  const [updateTodo, setUpdateTodo] = useState("");
  //Yup validation shcema
  const todoSchema = Yup.object().shape({
    todoist: Yup.string()
      .min(2, "To short!")
      .max(50, "To Long!")
      .required("Requried!"),
  });
  // Start Page
  useEffect(() => {
    todoService
      .getAll()
      .then((result) => setTodos(result.data.data))
      .catch();
  }, []);

  // Completed Update
  const handleCheckboxChange = async (id) => {
    try {
      // Önce tamamlanmışlığı güncelle
      await todoService.completedUpdate(id);

      // Ardından güncellenmiş todo listesini al
      const updatedTodos = await todoService.getAll();
      await setTodos(updatedTodos.data.data);
    } catch (error) {
      console.error("Hata oluştu: ", error);
    }
  };

  //Delete
  const handleDeleteTodo = async (id) => {
    await todoService.removeTodo(id);
    await todoService.getAll().then((res) => {
      setTodos(res.data.data);
    });
  };
  //All Todo
  const handleCompletedTodos = async () => {
    await todoService
      .getCompletedTodos()
      .then((result) => setTodos(result.data.data))
      .catch();
  };

  const handleUpdateTodo = async (id) => {
    await setUpdateForm(true);
    await todoService
      .getByTodo(id)
      .then((res) => {
        setUpdateTodo(res.data.data);
        console.log(updateTodo);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const cancelUpdateForm = async () => {
    await setUpdateForm(false);
    await setUpdateTodo("");
  };
  const handleTodos = async () => {
    await todoService
      .getTodos()
      .then((result) => setTodos(result.data.data))
      .catch();
  };

  const handleGetAll = async () => {
    await todoService
      .getAll()
      .then((result) => setTodos(result.data.data))
      .catch();
  };

  const deleteAllTasks = async()=>{
    await todoService.deleteAllTasks();
    await todoService.getAll().then((res) => {
      setTodos(res.data.data);
    });
  }

  const deleteDoneTasks = async()=>{
    await todoService.deleteDoneTasks();
    await todoService.getAll().then((res) => {
      setTodos(res.data.data);
    });

  }
  return (
    <>
      <Container>
        <div className="mt-3">
          <h3>
            <b>TodoInput</b>
          </h3>
          <div className="container mt-4">
            <div className="border p-3">
              <div className="input-group mb-3">
                <div className="input-group-prepend">
                  <span
                    className="input-group-text"
                    style={{ backgroundColor: "blue" }}
                  >
                    <i
                      className="fa fa-book text-white"
                      style={{ fontSize: "20px" }}
                    ></i>
                  </span>
                </div>
                {updateForm ? (
                  <Formik
                    initialValues={{ todoist: "" }}
                    validationSchema={todoSchema}
                    onSubmit={async (values, { resetForm }) => {
                      console.log(values);
                      await todoService.updateTodo(updateTodo.todoId, values);
                      await resetForm();
                      await todoService
                        .getAll()
                        .then((result) => setTodos(result.data.data))
                        .catch();
                      await cancelUpdateForm();
                    }}
                  >
                    <Form>
                      <FormGroup>
                        <Field
                          placeHolder="Todo to update"
                          name="todoist"
                          type="text"
                          className="form-control mb-3"
                          style={{ width: "1020px" }}
                        ></Field>
                        <ErrorMessage
                          name="todoist"
                          render={(error) => (
                            <span className="text text-danger">{error}</span>
                          )}
                        ></ErrorMessage>
                      </FormGroup>

                      <button
                        type="submit"
                        className="btn btn-warning mt-2"
                        style={{
                          width: "100%",
                          display: "block",
                          margin: "0 auto",
                        }}
                      >
                        Update
                      </button>
                      <button
                        className="btn btn-danger mt-2"
                        style={{
                          width: "100%",
                          display: "block",
                          margin: "0 auto",
                        }}
                        onClick={async () => cancelUpdateForm()}
                      >
                        Cancel
                      </button>
                    </Form>
                  </Formik>
                ) : (
                  <Formik
                    initialValues={{ todoist: "" }}
                    validationSchema={todoSchema}
                    onSubmit={async (values, { resetForm }) => {
                      console.log(values);
                      await todoService.addTodo(values);
                      await resetForm();
                      await todoService
                        .getAll()
                        .then((result) => setTodos(result.data.data))
                        .catch();
                    }}
                  >
                    <Form>
                      <FormGroup>
                        <Field
                          placeHolder="Todo to add"
                          name="todoist"
                          type="text"
                          className="form-control mb-3"
                          style={{ width: "1020px" }}
                        ></Field>
                        <ErrorMessage
                          name="todoist"
                          render={(error) => (
                            <span className="text text-danger">{error}</span>
                          )}
                        ></ErrorMessage>
                      </FormGroup>

                      <button
                        type="submit"
                        className="btn btn-primary mt-2"
                        style={{
                          width: "100%",
                          display: "block",
                          margin: "0 auto",
                        }}
                      >
                        Add new task
                      </button>
                    </Form>
                  </Formik>
                )}
              </div>
            </div>
          </div>
        </div>
        <div className="mt-3">
          <h3>
            <b>TodoList</b>
          </h3>
          <div className="d-flex justify-content-between">
            <button
              type="button"
              onClick={async () => handleGetAll()}
              className="btn btn-primary"
              style={{ width: "30%", display: "block" }}
            >
              All
            </button>
            <button
              type="button"
              className="btn btn-primary"
              onClick={async () => handleCompletedTodos()}
              style={{ width: "30%", display: "block" }}
            >
              Done
            </button>
            <button
              type="button"
              className="btn btn-primary"
              onClick={async () => handleTodos()}
              style={{ width: "30%", display: "block" }}
            >
              Todo
            </button>
          </div>
        </div>

        {todos.length > 0 ? (
          todos.map((todo, index) => (
            <div className="border p-2 mt-3" key={index}>
              <div className="d-flex align-items-center justify-content-between">
                <div>
                  <p
                    className={
                      todo.completed
                        ? "text-danger text-decoration-line-through"
                        : ""
                    }
                  >
                    {todo.todoist}
                  </p>
                </div>
                <div className="d-flex align-items-center">
                  <input
                    type="checkbox"
                    className="mr-2"
                    style={{ margin: "10px", cursor: "pointer" }}
                    checked={todo.completed}
                    onChange={async () => handleCheckboxChange(todo.todoId)}
                  />
                  <span
                    className="input-group-text"
                    style={{ margin: "10px", cursor: "pointer" }}
                  >
                    <i
                      onClick={async () => handleDeleteTodo(todo.todoId)}
                      className="fa fa-trash"
                    ></i>
                  </span>
                  <span
                    className="input-group-text"
                    style={{ margin: "10px", cursor: "pointer" }}
                  >
                    <i
                      className="fa fa-pencil"
                      onClick={async () => handleUpdateTodo(todo.todoId)}
                    ></i>
                  </span>
                </div>
              </div>
            </div>
          ))
        ) : (
          <div className="alert alert-warning mt-3">Todo not found!</div>
        )}
        <div className="d-flex justify-content-between mt-3">
          <button
            type="button"
            className="btn btn-danger"
            style={{ width: "40%", display: "block" }}
            onClick={async ()=> deleteDoneTasks()}
          >
            Delete Done Task
          </button>
          <button
            type="button"
            className="btn btn-danger"
            style={{ width: "40%", display: "block" }}
            onClick={async ()=> deleteAllTasks()}
          >
            Delete All Task
          </button>
        </div>
      </Container>
    </>
  );
}
