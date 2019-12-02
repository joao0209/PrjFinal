import React, { Component } from 'react'
import Grid from '@material-ui/core/Grid';
import { Button, Typography } from '@material-ui/core';
import { withStyles } from '@material-ui/core/styles';



function getModalStyle() {
    const top = 50;
    const left = 50;

    return {
        top: `${top}%`,
        left: `${left}%`,
        transform: `translate(-${top}%, -${left}%)`,
    };
}

const styles = theme => ({
    root: {
        flexGrow: 1,
        maxWidth: 752,
    },
    demo: {
        backgroundColor: theme.palette.background.paper,
    },
    title: {
        margin: `${theme.spacing.unit * 4}px 0 ${theme.spacing.unit * 2}px`,
    },
    paper: {
        position: 'absolute',
        width: theme.spacing.unit * 50,
        backgroundColor: theme.palette.background.paper,
        boxShadow: theme.shadows[5],
        padding: theme.spacing.unit * 4,
        outline: 'none',
    },
});

export default withStyles(styles)(class InsideModalAssumir extends Component {
    onClickHandler = async () => {
        await fetch(`http://localhost:8082/api/os/assumir/${this.props.instalador.id}`, { method: "PUT", headers: {
            'Content-Type': 'application/json'
            // 'Content-Type': 'application/x-www-form-urlencoded',
          }, body: JSON.stringify(this.props.ordemS) });
        this.props.triggerUpdate();
        this.props.onClose();
    }


    render() {
        const { classes } = this.props;
        return (
            <Grid container className={classes.paper} style={getModalStyle()} direction="column" spacing={2} alignItems="center">
                <Grid item>
                    <Typography>Cliente: {this.props.ordemS.cliente.nome}</Typography>
                </Grid>
                <Grid item>
                    <Typography>Tipo de plano: {this.props.ordemS.plano}</Typography>
                </Grid>
                <Grid item>
                    <Typography>Melhor Horario: {this.props.ordemS.horario ? this.props.ordemS.horario : 'Não definido'}</Typography>
                </Grid>
                <Grid item>
                    <Typography>Data de contratação: {this.props.ordemS.dataContratacao}</Typography>
                </Grid>
                <Grid item>
                    <Typography>Endereço: {this.props.ordemS.endereco.logradouro} {this.props.ordemS.endereco.numero}, {this.props.ordemS.endereco.bairro} CEP: {this.props.ordemS.endereco.cep}</Typography>
                </Grid>
                <Grid item xs={12}>
                    <Button variant="contained" id="btnAdicionar" onClick={this.onClickHandler}>
                        <Typography>Assumir Ordem de Serviço</Typography>
                    </Button>
                </Grid>
            </Grid>
        )
    }
})
