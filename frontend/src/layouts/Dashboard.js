import React from "react";
import Navi from "./Navi";
import TodoList from "../pages/TodoList";
import { Route, Routes } from "react-router-dom";


export default function Dashboard() {
  return (
    <>

        <Navi></Navi>
        <Routes>
        <Route  path="/" Component={TodoList}></Route>
        </Routes>
 
    </>
  );
}
