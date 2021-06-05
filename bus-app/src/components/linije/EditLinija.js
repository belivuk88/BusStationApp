import React from "react";
import {Button, Form} from "react-bootstrap";

import BusAxios from "../../apis/BusAxios";

class EditLinija extends React.Component {
    constructor(props){
        super(props);

        let linija = {
            brojMesta: 0,
            cenaKarte: 0.00,
            destinacija: "",
            vremePolaska: "",
            prevoznikId: null,
        }

        this.state ={
            linija:linija,
            prevoznici: [],
        };
    }

    componentDidMount(){
        this.getData();

    }
    async getData(){
        await this.getLinija();
        await this.getPrevoznici();

    }
    async getLinija(){
        try{
            let result = await BusAxios.get("/linije/" + this.props.match.params.id);
            if(result && result.status === 200){
                this.setState({
                    linija: result.data
                });
            }
        } catch(error){
            alert("Nije uspelo dobavljanje.");
        }
    }

    async getPrevoznici(){
        try{
            let result = await BusAxios.get("/prevoznici");
            if(result && result.status ===200){
                this.setState({
                    prevoznici: result.data,
                });
            }
        }catch(error){
            alert("Nije uspelo dobavljanje.");
        }
    }

    async doEdit(){
        try{
            await BusAxios.put("/linije/" + this.props.match.params.id, this.state.linija);
            this.props.history.push("/linije");
        }catch (error){
            alert("Nije uspelo cuvanje.");
        }
    }

    valueInputChange(event){
        let control = event.target;

        let name = control.name;
        let value = control.value;

        let linija = this.state.linija;
        linija[name] = value;

        this.setState({ linija: linija});
    }

    render(){
        return(
            <div>
                <h1>Izmeni linije</h1>
                <Form>
                    <Form.Group>
                        <Form.Label>Broj mesta</Form.Label>
                        <Form.Control
                        onChange={(event) => this.valueInputChange(event)}
                        name="brojMesta"
                        value={this.state.linija.brojMesta}
                        as="input"
                        ></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Cena karte</Form.Label>
                        <Form.Control
                        onChange={(event) => this.valueInputChange(event)}
                        name="cenaKarte"
                        value={this.state.linija.cenaKarte}
                        as="input"
                        ></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Destinacija</Form.Label>
                        <Form.Control
                        onChange={(event) => this.valueInputChange(event)}
                        name="destinacija"
                        value={this.state.linija.destinacija}
                        as="input"
                        ></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Vreme polaska</Form.Label>
                        <Form.Control
                        onChange={(event) => this.valueInputChange(event)}
                        name="vremePolaska"
                        value={this.state.linija.vremePolaska}
                        as="input"
                        ></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Prevoznik</Form.Label>
                        <Form.Control
                        onChange={(event) => this.valueInputChange(event)}
                        name="destinacija"
                        value={this.state.linija.prevoznikId}
                        as="select"
                        >
                            <option value={null}></option>
                            {this.state.prevoznici.map((prevoznik) => {
                                return(
                                    <option value = {prevoznik.id} key={prevoznik.id}>
                                        {prevoznik.naziv}
                                    </option>  
                                );
                            })}
                        </Form.Control>
                    </Form.Group>

                    <Button variant ="primary" onClick={() => this.doEdit()}>
                        Edit
                    </Button>
                </Form>
            </div>
        );
    }
}

export default EditLinija;