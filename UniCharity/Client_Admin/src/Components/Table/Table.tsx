/* eslint-disable @typescript-eslint/no-explicit-any */
/* eslint-disable @typescript-eslint/no-unused-vars */
type Props = {
  configs: any;
  data: any;
  onClickRecord?: (number: number) => void | undefined;
};

const Table = ({ configs, data, onClickRecord }: Props) => {
  const renderedRows = data.map((item: any, index: number) => {
    // const id = item.id || item.cam || item.slideId
    return (
      <tr
        style={{ cursor: "pointer" }}
        className="shadow-sm"
        key={index}
        // onClick={() => { onClickRecord && onClickRecord(id)}}
      >
        {configs.map((config: any, i: number) => {
          return <td key={i}>{config.render(item, index)}</td>;
        })}
      </tr>
    );
  });

  const renderedHeaders = configs.map((config: any, index: number) => {
    return (
      <th
        className=" p-4 text-xs font-medium text-gray-500 uppercase tracking-wider  align-items-center"
        key={index}
      >
        {config.label}
      </th>
    );
  });

  return (
    <div className="col-12">
      <div className="shadow bg-light rounded h-100 p-4">
        <div className="table-responsive">
          <table className="table">
            <thead>
              <tr className="">{renderedHeaders}</tr>
            </thead>
            <tbody>{data && renderedRows}</tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default Table;

{
  /* <div class="col-12">
  <div class="bg-light rounded h-100 p-4">
    <div class="table-responsive">
      <table class="table">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">First Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">Email</th>
            <th scope="col">Country</th>
            <th scope="col">ZIP</th>
            <th scope="col">Status</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <th scope="row">1</th>
            <td>John</td>
            <td>Doe</td>
            <td>jhon@email.com</td>
            <td>USA</td>
            <td>123</td>
            <td>Member</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</div>; */
}
