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

export default withStyles(styles)(class InsideModalDetalhar extends Component {
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
                    <Typography>Data de contratação: {this.props.ordemS.dataContratacao ? this.props.ordemS.dataContratacao.split('T')[0] : null}</Typography>
                </Grid>
                <Grid item>
                    <Typography>Endereço: {this.props.ordemS.endereco.logradouro} {this.props.ordemS.endereco.numero}, {this.props.ordemS.endereco.bairro} CEP: {this.props.ordemS.endereco.cep}</Typography>
                </Grid>
                <Grid item>
                    <Typography>Data de Inicio: {this.props.ordemS.dataInicioIntalacao ? this.props.ordemS.dataInicioIntalacao.split('T')[0] : 'Não definido'} </Typography>
                </Grid>
                <Grid item>
                    <Typography>Data de Termino: {this.props.ordemS.dataFinalInstalacao ?  this.props.ordemS.dataFinalInstalacao.split('T')[0] : 'Não definido'} </Typography>
                </Grid>
            </Grid>
        )
    }
})
