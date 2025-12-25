function LocationList() {
  const locations = ["Goa", "Manali", "Ooty"];

  return (
    <div>
      <h3>Select Location</h3>
      <select>
        {locations.map((loc) => (
          <option key={loc}>{loc}</option>
        ))}
      </select>
    </div>
  );
}

export default LocationList;
