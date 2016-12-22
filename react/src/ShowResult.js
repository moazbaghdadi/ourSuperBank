import React from 'react'

class ShowResult extends React.Component {
  constructor (props) {
    super(props);
  }

  render () {
    return (
      <div className="ResultInfo">
        <br />
        <br />
        <span>Account Number: </span><span>{this.props.accountNumber}</span><br />
        <span>Balance: </span><span>{this.props.balance}</span>
      </div>
    );
  }
}

export default ShowResult;
