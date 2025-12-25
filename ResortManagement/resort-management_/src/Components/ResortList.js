function ResortList() {
  const resorts = [
    { id: 1, name: "Sea View Resort" },
    { id: 2, name: "Mountain Bliss" },
  ];

  return (
    <div>
      <h3>Available Resorts</h3>
      <ul>
        {resorts.map((r) => (
          <li key={r.id}>{r.name}</li>
        ))}
      </ul>
    </div>
  );
}

export default ResortList;
