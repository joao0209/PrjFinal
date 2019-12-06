import React, { Component } from 'react'
import {
    Typography
} from '@material-ui/core';
export default class Header extends Component {

    printLista(lista){
        return lista.map((instalador) => <option key={instalador.id} value={instalador.id}>{instalador.nome}</option>);
    }
    render() {
        return (
            <div>
                <Typography>
                    Instalador: 
                </Typography>
                <select onChange={async (e) => { await this.props.onChangeS(e.target.value)}}>
                    {this.printLista(this.props.lista)}
                </select>
            </div>
        )
    }
}
