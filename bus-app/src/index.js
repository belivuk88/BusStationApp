import React from 'react';
import {Container, Button, Form, ButtonGroup, Navbar, Nav} from "react-bootstrap"
import ReactDOM from 'react-dom';
import{ Route, Link, HashRouter as Router, Switch} from 'react-router-dom';
import Home from './components/Home';
import EditLinija from './components/linije/EditLinija';
import Linija from './components/linije/Linija';
import Login from './components/login/Login';
import {logout} from './services/auth';

class App extends React.Component {
    render(){
        return (
            <div style ={{
                
            backgroundImage: `url(${process.env.PUBLIC_URL + '/bus.jpg'})`,
            backgroundPosition: 'center',
    backgroundSize: 'cover',
    backgroundRepeat: 'no-repeat',
    width: '100vw',
    height: '100vh'}}>
            <div>
                <Router>
                <Navbar expand bg="dark" variant="dark">
                <Navbar.Brand as={Link} to="/"><img
        src="/bus-logo.jpg"
        width="60"
        height="30"
        className="d-inline-block align-top"
        alt="React Bootstrap logo"
      /></Navbar.Brand>
                        <Nav className= "mr-auto">
                            <Nav.Link as={Link} to="/linije">Linije</Nav.Link>
                            </Nav>
                            {window.localStorage['jwt'] ? 
                            <Button onClick = {()=>logout()}>Logout</Button> :
                            <Nav.Link as={Link} to="/login">Log in</Nav.Link>
                            }
                        
                    </Navbar>
                    <Container style={{paddingTop:"25px"}}>
                    <Switch>
                        <Route exact path = "/" component = {Home}/>
                        <Route exact path = "/login" component = {Login}/>
                        <Route exact path = "/linije" component = {Linija}/>
                        <Route exact path="/linije/edit/:id" component={EditLinija} />
                    </Switch>
                    </Container>
                </Router>
                </div>
            </div>
        );
    }


    
};

ReactDOM.render(<App/>, document.querySelector("#root"));