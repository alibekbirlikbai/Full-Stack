import logo from './logo.svg';
import './App.css';
import {
  BrowserRouter,
  Switch,
  Route,
  Link
} from "react-router-dom";
import  LoginPage from './pages/LoginPage';
import { Dashboard } from './pages/dashboard/dashboard';

import ListUserComponent from './pages/dashboard/components/ListUserComponent';
import HeaderComponent from './pages/dashboard/components/HeaderComponent';
import FooterComponent from './pages/dashboard/components/FooterComponent';
import CreateUserComponent from './pages/dashboard/components/CreateUserComponent';
import UpdateUserComponent from './pages/dashboard/components/UpdateUserComponent';
import ViewUserComponent from './pages/dashboard/components/ViewUserComponent';


function App() {
  return (
      <div>
          <BrowserRouter>
            <HeaderComponent/>
              <div class="container">
                  <Switch>
                    <Route exact path="/" component={LoginPage}/>
                    <Route exact path="/dashboard" component={Dashboard}/>

                    {/* <Route path = "/" exact component = {ListUserComponent}></Route> */}
                    {/* <Route path = "/users" component = {ListUserComponent}></Route> */}
                    <Route path = "/add-user/:id" component = {CreateUserComponent}></Route>
                    <Route path = "/view-user/:id" component = {ViewUserComponent}></Route>
                  </Switch>
              </div>
              <FooterComponent />
        </BrowserRouter>
      </div>
  );
}

export default App;
