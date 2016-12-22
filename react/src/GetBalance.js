import React from 'react'

class GetBalance extends React.Component {
  constructor (props) {
    super(props);
    this.state = {accountNumber:'', amount:0};
  }

  render () {
    return (
      <div className="RequestInfo">
        <div className="operation">
          <span>get balance:</span><br />
          <input id="getBalanceInput" onKeyUp={this.props.onKeyUpBalance} type="text" placeholder="Insert Account Number"/><br />
          <button id="getBalanceButton" onClick={this.props.getBalance_onClick}>get balance</button>
        </div>
        <div className="operation">
          <span>operations:</span><br />
          <input id="getBalanceInput" onKeyUp={this.props.onKeyUpAmount} type="text" placeholder="Insert Amount"/><br />
          <button id="getBalanceButton" onClick={this.props.deposit}>deposit</button>
          <button id="getBalanceButton" onClick={this.props.withdrawal}>withdrawal</button>
        </div>
      </div>
    );
  }
}

export default GetBalance;
