import React, { useState } from "react";
    /**
    * Form component in React.
    *
    * @returns a React form component with three input fields and a submit button.
    */

function Form(props) {
  const initialState = {
    Field01: "",
    Field02: "",
    Field03: "",
  };
  const [state, setState] = useState(initialState);

  const handleChange = (event) => {
    const { name, value } = event.target;
    setState({ ...state, [name]: value });
  };

  const submitForm = () => {
    props.handleSubmit(state);
    setState(initialState);
  };

  return (
    <form>
      <label>Field01</label>
      <input
        type="text"
        name="Field01"
        value={state.Field01}
        onChange={handleChange}
      />
      <label>Field02</label>
      <input
        type="text"
        name="Field02"
        value={state.Field02}
        onChange={handleChange}
      />
      <label>Field03</label>
      <input
        type="text"
        name="Field03"
        value={state.Field03}
        onChange={handleChange}
      />
      <input type="button" value="Submit" onClick={submitForm} />
    </form>
  );
}

export default Form;