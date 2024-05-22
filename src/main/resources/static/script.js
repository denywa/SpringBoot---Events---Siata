document.addEventListener("DOMContentLoaded", function () {
  const eventForm = document.getElementById("eventForm");
  const eventList = document.getElementById("eventList");
  const imgInput = document.getElementById("imgInput");

  function loadEvents() {
    fetch("/api/events")
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.json();
      })
      .then((events) => {
        eventList.innerHTML = "";
        events.forEach((event) => {
          const li = document.createElement("li");
          li.textContent = `${event.eventName} - ${event.eventDescription} - ${event.eventDate} - ${event.eventTime} - ${event.location} - ${event.eventImg}`;
          eventList.appendChild(li);
        });
      })
      .catch((error) => {
        console.error("There has been a problem with your fetch operation:", error);
      });
  }

  eventForm.addEventListener("submit", function (e) {
    e.preventDefault();
    const formData = new FormData(eventForm);
    const file = imgInput.files[0];

    // Validasi ukuran file
    if (file.size > 5000000) {
      // Batas ukuran file 5MB
      alert("Ukuran gambar terlalu besar. Harap unggah gambar yang lebih kecil dari 5MB.");
      return;
    }

    const reader = new FileReader();

    reader.readAsDataURL(file);
    reader.onload = function () {
      formData.set("eventImg", reader.result); // Simpan gambar sebagai string Base64

      const jsonData = {};
      formData.forEach((value, key) => {
        jsonData[key] = value;
      });

      // Pastikan gambar sudah terkodekan dalam Base64 sebelum mengirim
      if (reader.result && reader.result.startsWith("data:image")) {
        fetch("/api/events", {
          method: "POST",
          body: JSON.stringify(jsonData),
          headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
          },
        })
          .then((response) => {
            if (!response.ok) {
              return response.json().then((err) => {
                throw new Error(err.message);
              });
            }
            return response.json();
          })
          .then((data) => {
            console.log(data.message);
            eventForm.reset();
            loadEvents();
          })
          .catch((error) => {
            console.error("There has been a problem with your fetch operation:", error);
          });
      } else {
        console.error("Gagal memuat gambar sebagai string Base64.");
      }
    };
  });

  loadEvents();
});